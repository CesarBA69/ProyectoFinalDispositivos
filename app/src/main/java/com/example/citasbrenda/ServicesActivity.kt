package com.example.citasbrenda

import android.app.AlertDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.citasbrenda.data.AppDatabase
import com.example.citasbrenda.data.ServiceEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ServicesActivity : AppCompatActivity() {

    private lateinit var container: LinearLayout
    private val db by lazy { AppDatabase.get(this) }
    private val dao by lazy { db.serviceDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services)

        container = findViewById(R.id.servicesContainer)

        findViewById<Button>(R.id.btnAddService).setOnClickListener {
            showServiceDialog()
        }

        loadServices()
    }

    private fun loadServices() {
        lifecycleScope.launch {
            val list = withContext(Dispatchers.IO) { dao.getAll() }

            while (container.childCount > 2) container.removeViewAt(2)

            if (list.isEmpty()) {
                container.addView(TextView(this@ServicesActivity).apply {
                    text = "No hay servicios registrados."
                })
                return@launch
            }

            list.forEach { s ->
                val row = LinearLayout(this@ServicesActivity).apply {
                    orientation = LinearLayout.VERTICAL
                    setPadding(16, 16, 16, 16)
                }

                val tvName = TextView(this@ServicesActivity).apply {
                    text = s.name
                    textSize = 16f
                }

                val tvDur = TextView(this@ServicesActivity).apply {
                    text = "Duración: ${s.durationMinutes} min"
                }

                val actions = LinearLayout(this@ServicesActivity).apply {
                    orientation = LinearLayout.HORIZONTAL
                }

                val btnEdit = Button(this@ServicesActivity).apply {
                    text = "Editar"
                    setOnClickListener { showServiceDialog(s) }
                }

                val btnDelete = Button(this@ServicesActivity).apply {
                    text = "Eliminar"
                    setOnClickListener { confirmDelete(s.id) }
                }

                actions.addView(btnEdit)
                actions.addView(btnDelete)

                row.addView(tvName)
                row.addView(tvDur)
                row.addView(actions)

                container.addView(row)
            }
        }
    }

    private fun showServiceDialog(existing: ServiceEntity? = null) {
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 20, 40, 10)
        }

        val etName = EditText(this).apply {
            hint = "Nombre del servicio"
            setText(existing?.name ?: "")
        }

        val etDuration = EditText(this).apply {
            hint = "Duración (min)"
            inputType = android.text.InputType.TYPE_CLASS_NUMBER
            setText((existing?.durationMinutes ?: 60).toString())
        }

        layout.addView(etName)
        layout.addView(etDuration)

        AlertDialog.Builder(this)
            .setTitle(if (existing == null) "Agregar servicio" else "Editar servicio")
            .setView(layout)
            .setPositiveButton("Guardar") { _, _ ->
                val name = etName.text.toString().trim()
                val dur = etDuration.text.toString().trim().toIntOrNull() ?: 60

                if (name.isEmpty()) {
                    Toast.makeText(this, "Nombre requerido", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        if (existing == null) {
                            dao.insert(ServiceEntity(name = name, durationMinutes = dur))
                        } else {
                            dao.update(existing.copy(name = name, durationMinutes = dur))
                        }
                    }
                    loadServices()
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun confirmDelete(id: Long) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar servicio")
            .setMessage("¿Seguro que deseas eliminarlo?")
            .setPositiveButton("Sí") { _, _ ->
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) { dao.deleteById(id) }
                    loadServices()
                }
            }
            .setNegativeButton("No", null)
            .show()
    }
}
