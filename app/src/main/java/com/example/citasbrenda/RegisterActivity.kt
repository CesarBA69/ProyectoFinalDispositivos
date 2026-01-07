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

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etNombre = findViewById<EditText>(R.id.editTextNombre)
        val etCorreo = findViewById<EditText>(R.id.editTextCorreo)
        val etPassword = findViewById<EditText>(R.id.editTextPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.editTextConfirmPassword)
        val btnContinuar = findViewById<Button>(R.id.btnContinuar)
        val tvVolverLogin = findViewById<TextView>(R.id.tvVolverLogin)

        val db = AppDatabase.get(this)
        val userDao = db.userDao()
        val session = SessionManager(this)

        btnContinuar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val correo = etCorreo.text.toString().trim().lowercase()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (nombre.isEmpty() || correo.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password != confirmPassword) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val exists = withContext(Dispatchers.IO) { userDao.findByEmail(correo) }
                if (exists != null) {
                    Toast.makeText(this@RegisterActivity, "Ese correo ya está registrado", Toast.LENGTH_SHORT).show()
                    return@launch
                }

                val user = UserEntity(
                    fullName = nombre,
                    email = correo,
                    passwordHash = Security.sha256(password),
                    role = UserRole.CLIENT // por defecto cliente
                )

                val newId = withContext(Dispatchers.IO) { userDao.insert(user) }
                session.saveUser(newId, UserRole.CLIENT)

                Toast.makeText(this@RegisterActivity, "Registro exitoso", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@RegisterActivity, HomeActivity::class.java))
                finish()
            }
        }

        tvVolverLogin.setOnClickListener { finish() }
    }
}


