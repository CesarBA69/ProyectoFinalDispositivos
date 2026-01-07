package com.example.citasbrenda

import android.app.AlertDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.citasbrenda.data.AppDatabase
import com.example.citasbrenda.data.PriceItemEntity
import com.example.citasbrenda.data.PriceItemWithService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PriceListDetailActivity : AppCompatActivity() {

    private lateinit var container: LinearLayout
    private lateinit var tvTitle: TextView

    private val db by lazy { AppDatabase.get(this) }
    private val priceDao by lazy { db.priceListDao() }
    private val serviceDao by lazy { db.serviceDao() }

    private var priceListId: Long = -1
    private var priceListName: String = "Lista"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_price_list_detail)

        container = findViewById(R.id.priceItemsContainer)
        tvTitle = findViewById(R.id.tvTitle)

        priceListId = intent.getLongExtra("PRICE_LIST_ID", -1)
        priceListName = intent.getStringExtra("PRICE_LIST_NAME") ?: "Lista"

        tvTitle.text = priceListName

        findViewById<Button>(R.id.btnAddItem).setOnClickListener {
            showAddItemDialog()
        }

        loadItems()
    }

    private fun loadItems() {
        lifecycleScope.launch {
            val items = withContext(Dispatchers.IO) { priceDao.getItems(priceListId) }

            while (container.childCount > 2) container.removeViewAt(2)

            if (items.isEmpty()) {
                container.addView(TextView(this@PriceListDetailActivity).apply {
                    text = "No hay precios en esta lista."
                })
                return@launch
            }

            items.forEach { item ->
                container.addView(renderItem(item))
            }
        }
    }

    private fun renderItem(item: PriceItemWithService): LinearLayout {
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        val tvService = TextView(this).apply {
            text = item.serviceName
            textSize = 16f
        }

        val tvPrice = TextView(this).apply {
            text = "Precio: $${"%.2f".format(item.price)}"
        }

        val actions = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
        }

        val btnEdit = Button(this).apply {
            text = "Editar"
            setOnClickListener { showEditPriceDialog(item) }
        }

        val btnDelete = Button(this).apply {
            text = "Eliminar"
            setOnClickListener { confirmDeleteItem(item.itemId) }
        }

        actions.addView(btnEdit)
        actions.addView(btnDelete)

        row.addView(tvService)
        row.addView(tvPrice)
        row.addView(actions)

        return row
    }

    private fun showAddItemDialog() {
        lifecycleScope.launch {
            val services = withContext(Dispatchers.IO) { serviceDao.getActive() }
            if (services.isEmpty()) {
                Toast.makeText(this@PriceListDetailActivity, "Primero crea servicios", Toast.LENGTH_SHORT).show()
                return@launch
            }

            val serviceNames = services.map { it.name }.toTypedArray()
            var selectedIndex = 0

            val layout = LinearLayout(this@PriceListDetailActivity).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(40, 20, 40, 10)
            }

            val spinner = Spinner(this@PriceListDetailActivity).apply {
                adapter = ArrayAdapter(
                    this@PriceListDetailActivity,
                    android.R.layout.simple_spinner_dropdown_item,
                    serviceNames
                )
                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                        selectedIndex = position
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
            }

            val etPrice = EditText(this@PriceListDetailActivity).apply {
                hint = "Precio (ej. 350)"
                inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
            }

            layout.addView(spinner)
            layout.addView(etPrice)

            AlertDialog.Builder(this@PriceListDetailActivity)
                .setTitle("Agregar precio")
                .setView(layout)
                .setPositiveButton("Guardar") { _, _ ->
                    val price = etPrice.text.toString().trim().toDoubleOrNull()
                    if (price == null || price <= 0) {
                        Toast.makeText(this@PriceListDetailActivity, "Precio inválido", Toast.LENGTH_SHORT).show()
                        return@setPositiveButton
                    }

                    val serviceId = services[selectedIndex].id

                    lifecycleScope.launch {
                        val ok = withContext(Dispatchers.IO) {
                            val exists = priceDao.existsItem(priceListId, serviceId)
                            if (exists) return@withContext false


                            priceDao.insertItem(
                                PriceItemEntity(
                                    priceListId = priceListId,
                                    serviceId = serviceId,
                                    price = price
                                )
                            )
                            true
                        }


                        if (!ok) {
                            Toast.makeText(
                                this@PriceListDetailActivity,
                                "Ese servicio ya tiene precio en esta lista",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        loadItems()
                    }
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }
    }

    private fun showEditPriceDialog(item: PriceItemWithService) {
        val et = EditText(this).apply {
            hint = "Nuevo precio"
            inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
            setText(item.price.toString())
        }

        AlertDialog.Builder(this)
            .setTitle("Editar precio")
            .setView(et)
            .setPositiveButton("Guardar") { _, _ ->
                val price = et.text.toString().trim().toDoubleOrNull()
                if (price == null || price <= 0) {
                    Toast.makeText(this, "Precio inválido", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        priceDao.updateItemPrice(item.itemId, price)
                    }
                    loadItems()
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun confirmDeleteItem(itemId: Long) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar precio")
            .setMessage("¿Seguro?")
            .setPositiveButton("Sí") { _, _ ->
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) { priceDao.deleteItem(itemId) }
                    loadItems()
                }
            }
            .setNegativeButton("No", null)
            .show()
    }
}
