package com.example.citasbrenda

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.citasbrenda.data.AppDatabase
import com.example.citasbrenda.data.Security
import com.example.citasbrenda.data.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChangePasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        val etCurrent = findViewById<EditText>(R.id.etCurrentPassword)
        val etNew = findViewById<EditText>(R.id.etNewPassword)
        val etConfirm = findViewById<EditText>(R.id.etConfirmNewPassword)
        val btnSave = findViewById<Button>(R.id.btnSavePassword)

        val session = SessionManager(this)
        val userId = session.getUserId()

        if (userId == -1L) {
            finish()
            return
        }

        val db = AppDatabase.get(this)
        val userDao = db.userDao()

        btnSave.setOnClickListener {
            val current = etCurrent.text.toString().trim()
            val newPass = etNew.text.toString().trim()
            val confirm = etConfirm.text.toString().trim()

            if (current.isEmpty() || newPass.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newPass.length < 6) {
                Toast.makeText(this, "La nueva contraseña debe tener mínimo 6 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newPass != confirm) {
                Toast.makeText(this, "Las contraseñas nuevas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val ok = withContext(Dispatchers.IO) {
                    val user = userDao.findById(userId) ?: return@withContext false
                    val currentHash = Security.sha256(current)
                    if (user.passwordHash != currentHash) return@withContext false

                    val newHash = Security.sha256(newPass)
                    userDao.updatePassword(userId, newHash) > 0
                }

                if (!ok) {
                    Toast.makeText(this@ChangePasswordActivity, "Contraseña actual incorrecta", Toast.LENGTH_SHORT).show()
                    return@launch
                }

                Toast.makeText(this@ChangePasswordActivity, "Contraseña actualizada ✅", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
