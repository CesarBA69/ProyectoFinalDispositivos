package com.example.citasbrenda

import android.app.AlertDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.citasbrenda.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProvidersActivity : AppCompatActivity() {

    private lateinit var container: LinearLayout
    private val db by lazy { AppDatabase.get(this) }
    private val userDao by lazy { db.userDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_providers)

        container = findViewById(R.id.providersContainer)

        findViewById<Button>(R.id.btnAddProvider).setOnClickListener {
            showProviderDialog()
        }

        loadProviders()
    }

    private fun loadProviders() {
        lifecycleScope.launch {
            val list = withContext(Dispatchers.IO) {
                userDao.getByRole(UserRole.PROVIDER)
            }

            // Limpia items (dejamos título + botón)
            while (container.childCount > 2) container.removeViewAt(2)

            if (list.isEmpty()) {
                container.addView(TextView(this@ProvidersActivity).apply {
                    text = "No hay prestadores registrados."
                })
                return@launch
            }

            list.forEach { u ->
                val row = LinearLayout(this@ProvidersActivity).apply {
                    orientation = LinearLayout.VERTICAL
                    setPadding(16, 16, 16, 16)
                }

                val tvName = TextView(this@ProvidersActivity).apply {
                    text = u.fullName + if (!u.isActive) " (INACTIVO)" else ""
                    textSize = 16f
                }

                val tvEmail = TextView(this@ProvidersActivity).apply {
                    text = u.email
                }

                val actions = LinearLayout(this@ProvidersActivity).apply {
                    orientation = LinearLayout.HORIZONTAL
                }

                val btnEdit = Button(this@ProvidersActivity).apply {
                    text = "Editar"
                    setOnClickListener { showProviderDialog(u) }
                }

                val btnToggle = Button(this@ProvidersActivity).apply {
                    text = if (u.isActive) "Desactivar" else "Activar"
                    setOnClickListener { toggleActive(u) }
                }

                val btnDelete = Button(this@ProvidersActivity).apply {
                    text = "Eliminar"
                    setOnClickListener { confirmDelete(u.id) }
                }

                actions.addView(btnEdit)
                actions.addView(btnToggle)
                actions.addView(btnDelete)

                row.addView(tvName)
                row.addView(tvEmail)
                row.addView(actions)

                container.addView(row)
            }
        }
    }

    private fun showProviderDialog(existing: UserEntity? = null) {
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 20, 40, 10)
        }

        val etName = EditText(this).apply {
            hint = "Nombre completo"
            setText(existing?.fullName ?: "")
        }

        val etEmail = EditText(this).apply {
            hint = "Correo"
            setText(existing?.email ?: "")
        }

        val etPassword = EditText(this).apply {
            hint = if (existing == null) "Contraseña" else "Nueva contraseña (opcional)"
            inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
        }

        layout.addView(etName)
        layout.addView(etEmail)
        layout.addView(etPassword)

        AlertDialog.Builder(this)
            .setTitle(if (existing == null) "Agregar prestador" else "Editar prestador")
            .setView(layout)
            .setPositiveButton("Guardar") { _, _ ->
                val name = etName.text.toString().trim()
                val email = etEmail.text.toString().trim().lowercase()
                val pass = etPassword.text.toString().trim()

                if (name.isEmpty() || email.isEmpty()) {
                    Toast.makeText(this, "Nombre y correo son obligatorios", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                lifecycleScope.launch {
                    val ok = withContext(Dispatchers.IO) {
                        // email duplicado
                        val existingEmailUser = userDao.findByEmail(email)
                        if (existingEmailUser != null && existingEmailUser.id != existing?.id) return@withContext false

                        if (existing == null) {
                            if (pass.isEmpty() || pass.length < 6) return@withContext false
                            userDao.insert(
                                UserEntity(
                                    fullName = name,
                                    email = email,
                                    passwordHash = Security.sha256(pass),
                                    role = UserRole.PROVIDER,
                                    isActive = true
                                )
                            )
                            true
                        } else {
                            userDao.updateBasic(existing.id, name, email)
                            if (pass.isNotEmpty()) {
                                if (pass.length < 6) return@withContext false
                                userDao.updatePassword(existing.id, Security.sha256(pass))
                            }
                            true
                        }
                    }

                    if (!ok) {
                        Toast.makeText(
                            this@ProvidersActivity,
                            "Error: correo duplicado o contraseña inválida (min 6)",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@launch
                    }

                    loadProviders()
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun toggleActive(user: UserEntity) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                userDao.setActive(user.id, !user.isActive)
            }
            loadProviders()
        }
    }

    private fun confirmDelete(userId: Long) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar prestador")
            .setMessage("¿Seguro que deseas eliminarlo?")
            .setPositiveButton("Sí") { _, _ ->
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) { userDao.deleteById(userId) }
                    loadProviders()
                }
            }
            .setNegativeButton("No", null)
            .show()
    }
}
