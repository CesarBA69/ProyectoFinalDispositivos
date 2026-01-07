package com.example.citasbrenda

import android.app.AlertDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.citasbrenda.data.AppDatabase
import com.example.citasbrenda.data.BranchEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BranchesActivity : AppCompatActivity() {

    private lateinit var container: LinearLayout
    private val db by lazy { AppDatabase.get(this) }
    private val dao by lazy { db.branchDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_branches)

        container = findViewById(R.id.branchesContainer)

        findViewById<Button>(R.id.btnAddBranch).setOnClickListener {
            showBranchDialog()
        }

        loadBranches()
    }

    private fun loadBranches() {
        lifecycleScope.launch {
            val list = withContext(Dispatchers.IO) { dao.getAll() }

            // Limpia items (dejamos título + botón)
            while (container.childCount > 2) container.removeViewAt(2)

            if (list.isEmpty()) {
                container.addView(TextView(this@BranchesActivity).apply {
                    text = "No hay sucursales registradas."
                })
                return@launch
            }

            list.forEach { b ->
                val row = LinearLayout(this@BranchesActivity).apply {
                    orientation = LinearLayout.VERTICAL
                    setPadding(16, 16, 16, 16)
                }

                val tvName = TextView(this@BranchesActivity).apply {
                    text = b.name
                    textSize = 16f
                }

                val tvAddr = TextView(this@BranchesActivity).apply {
                    text = b.address ?: "Sin dirección"
                }

                val actions = LinearLayout(this@BranchesActivity).apply {
                    orientation = LinearLayout.HORIZONTAL
                }

                val btnEdit = Button(this@BranchesActivity).apply {
                    text = "Editar"
                    setOnClickListener { showBranchDialog(b) }
                }

                val btnDelete = Button(this@BranchesActivity).apply {
                    text = "Eliminar"
                    setOnClickListener { confirmDelete(b.id) }
                }

                actions.addView(btnEdit)
                actions.addView(btnDelete)

                row.addView(tvName)
                row.addView(tvAddr)
                row.addView(actions)

                container.addView(row)
            }
        }
    }

    private fun showBranchDialog(existing: BranchEntity? = null) {
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 20, 40, 10)
        }

        val etName = EditText(this).apply {
            hint = "Nombre sucursal"
            setText(existing?.name ?: "")
        }

        val etAddr = EditText(this).apply {
            hint = "Dirección"
            setText(existing?.address ?: "")
        }

        layout.addView(etName)
        layout.addView(etAddr)

        AlertDialog.Builder(this)
            .setTitle(if (existing == null) "Agregar sucursal" else "Editar sucursal")
            .setView(layout)
            .setPositiveButton("Guardar") { _, _ ->
                val name = etName.text.toString().trim()
                val addr = etAddr.text.toString().trim().ifEmpty { null }

                if (name.isEmpty()) {
                    Toast.makeText(this, "Nombre requerido", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        if (existing == null) {
                            dao.insert(BranchEntity(name = name, address = addr))
                        } else {
                            dao.update(existing.copy(name = name, address = addr))
                        }
                    }
                    loadBranches()
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun confirmDelete(id: Long) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar sucursal")
            .setMessage("¿Seguro que deseas eliminarla?")
            .setPositiveButton("Sí") { _, _ ->
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) { dao.deleteById(id) }
                    loadBranches()
                }
            }
            .setNegativeButton("No", null)
            .show()
    }
}
