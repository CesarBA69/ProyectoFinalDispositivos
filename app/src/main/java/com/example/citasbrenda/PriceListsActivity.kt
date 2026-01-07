package com.example.citasbrenda

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.citasbrenda.data.AppDatabase
import com.example.citasbrenda.data.PriceListEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PriceListsActivity : AppCompatActivity() {

    private lateinit var container: LinearLayout
    private val db by lazy { AppDatabase.get(this) }
    private val dao by lazy { db.priceListDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_price_lists)

        container = findViewById(R.id.priceListsContainer)

        findViewById<Button>(R.id.btnAddPriceList).setOnClickListener {
            showCreateListDialog()
        }

        loadLists()
    }

    private fun loadLists() {
        lifecycleScope.launch {
            val lists = withContext(Dispatchers.IO) { dao.getAllLists() }

            while (container.childCount > 2) container.removeViewAt(2)

            if (lists.isEmpty()) {
                container.addView(TextView(this@PriceListsActivity).apply {
                    text = "No hay listas registradas."
                })
                return@launch
            }

            lists.forEach { pl ->
                val row = LinearLayout(this@PriceListsActivity).apply {
                    orientation = LinearLayout.VERTICAL
                    setPadding(16, 16, 16, 16)
                }

                val tvName = TextView(this@PriceListsActivity).apply {
                    text = pl.name + if (pl.isActive) " (ACTIVA)" else ""
                    textSize = 16f
                }

                val actions = LinearLayout(this@PriceListsActivity).apply {
                    orientation = LinearLayout.HORIZONTAL
                }

                val btnOpen = Button(this@PriceListsActivity).apply {
                    text = "Abrir"
                    setOnClickListener {
                        startActivity(
                            Intent(this@PriceListsActivity, PriceListDetailActivity::class.java)
                                .putExtra("PRICE_LIST_ID", pl.id)
                                .putExtra("PRICE_LIST_NAME", pl.name)
                        )
                    }
                }

                val btnActivate = Button(this@PriceListsActivity).apply {
                    text = if (pl.isActive) "Activa" else "Activar"
                    isEnabled = !pl.isActive
                    setOnClickListener { activate(pl) }
                }

                val btnDelete = Button(this@PriceListsActivity).apply {
                    text = "Eliminar"
                    setOnClickListener { confirmDelete(pl.id) }
                }

                actions.addView(btnOpen)
                actions.addView(btnActivate)
                actions.addView(btnDelete)

                row.addView(tvName)
                row.addView(actions)

                container.addView(row)
            }
        }
    }

    private fun showCreateListDialog() {
        val et = EditText(this).apply { hint = "Nombre de la lista (ej. Enero 2026)" }

        AlertDialog.Builder(this)
            .setTitle("Crear lista de precio")
            .setView(et)
            .setPositiveButton("Crear") { _, _ ->
                val name = et.text.toString().trim()
                if (name.isEmpty()) {
                    Toast.makeText(this, "Nombre requerido", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        dao.insertList(PriceListEntity(name = name, isActive = false))
                    }
                    loadLists()
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun activate(list: PriceListEntity) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                dao.deactivateAll()
                dao.updateList(list.copy(isActive = true))
            }
            loadLists()
        }
    }

    private fun confirmDelete(id: Long) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar lista")
            .setMessage("Se eliminará la lista y sus precios. ¿Continuar?")
            .setPositiveButton("Sí") { _, _ ->
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) { dao.deleteList(id) }
                    loadLists()
                }
            }
            .setNegativeButton("No", null)
            .show()
    }
}
