package com.example.citasbrenda

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.citasbrenda.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 🌱 SEED DE LA BASE DE DATOS (se ejecuta solo una vez)
        lifecycleScope.launch(Dispatchers.IO) {
            DatabaseSeeder.seedIfNeeded(this@LoginActivity)
        }

        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvRegistrar = findViewById<TextView>(R.id.tvRegistrar)

        val db = AppDatabase.get(this)
        val userDao = db.userDao()
        val session = SessionManager(this)

        btnLogin.setOnClickListener {
            val correo = etCorreo.text.toString().trim().lowercase()
            val password = etPassword.text.toString().trim()

            if (correo.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val user = withContext(Dispatchers.IO) {
                    userDao.findByEmail(correo)
                }

                if (user == null || user.passwordHash != Security.sha256(password)) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Credenciales incorrectas",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@launch
                }

                if (!user.isActive) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Usuario desactivado",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@launch
                }

                // 💾 Guardar sesión
                session.saveUser(user.id, user.role)

                // 🚀 Ir al Home (ahí se decide por rol)
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                finish()
            }
        }

        tvRegistrar.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}



