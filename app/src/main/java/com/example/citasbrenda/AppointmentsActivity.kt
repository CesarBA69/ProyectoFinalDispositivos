package com.example.citasbrenda

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.citasbrenda.data.AppDatabase
import com.example.citasbrenda.data.SessionManager
import com.example.citasbrenda.data.UserRole
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AppointmentsActivity : AppCompatActivity() {

    private val df = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointments)

        val container = findViewById<LinearLayout>(R.id.appointmentsContainer)

        val session = SessionManager(this)
        val role = session.getRole()
        val userId = session.getUserId()

        if (role == null || userId == -1L) {
            finish()
            return
        }

        val db = AppDatabase.get(this)
        val appointmentDao = db.appointmentDao()

        lifecycleScope.launch {
            val appointments = withContext(Dispatchers.IO) {
                when (role) {
                    UserRole.PROVIDER -> appointmentDao.getByProvider(userId)
                    UserRole.CLIENT -> appointmentDao.getByClient(userId)
                    else -> emptyList()
                }
            }

            // Limpia todo menos el título (posición 0)
            while (container.childCount > 1) {
                container.removeViewAt(1)
            }

            if (appointments.isEmpty()) {
                container.addView(TextView(this@AppointmentsActivity).apply {
                    text = "No hay citas registradas."
                    textSize = 16f
                })
                return@launch
            }

            appointments.forEach { appt ->
                val subtitle = if (role == UserRole.PROVIDER) {
                    "Cliente: ${appt.clientName} • Tel: ${appt.clientPhone ?: "N/A"}"
                } else {
                    "Prestador: ${appt.providerName}"
                }

                val item = AppointmentItemView(this@AppointmentsActivity).apply {
                    setTitle("${appt.serviceName} • $${"%.2f".format(appt.servicePrice)}")
                    setSubtitle(subtitle)
                    setDate("Fecha: ${df.format(Date(appt.startTimeMillis))}")
                    setStatus("Estado: ${appt.status}")

                    // Abrir detalle
                    setOnClickListener {
                        val i = Intent(this@AppointmentsActivity, AppointmentDetailActivity::class.java)
                        i.putExtra("appointmentId", appt.id)
                        startActivity(i)
                    }

                    // Cancelar cita
                    setOnCancelClick {
                        if (appt.status == "CANCELADA") {
                            Toast.makeText(context, "Ya está cancelada", Toast.LENGTH_SHORT).show()
                            return@setOnCancelClick
                        }

                        AlertDialog.Builder(context)
                            .setTitle("Cancelar cita")
                            .setMessage("¿Seguro que deseas cancelar esta cita?")
                            .setPositiveButton("Sí") { _, _ ->
                                lifecycleScope.launch {
                                    withContext(Dispatchers.IO) {
                                        appointmentDao.cancel(appt.id)
                                    }
                                    Toast.makeText(context, "Cita cancelada", Toast.LENGTH_SHORT).show()
                                    recreate()
                                }
                            }
                            .setNegativeButton("No", null)
                            .show()
                    }
                }

                container.addView(item)
            }
        }
    }
}


