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
import com.example.citasbrenda.data.AppointmentEntity
import com.example.citasbrenda.data.SessionManager
import com.example.citasbrenda.data.UserRole
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

class MyAppointmentsActivity : AppCompatActivity() {

    private val df = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

    private lateinit var container: LinearLayout

    private val db by lazy { AppDatabase.get(this) }
    private val appointmentDao by lazy { db.appointmentDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_appointments)

        container = findViewById(R.id.containerAppointments)

        load()
    }

    private fun load() {
        val session = SessionManager(this)
        val userId = session.getUserId()
        val role: UserRole? = session.getRole()

        if (role == null || userId == -1L) {
            Toast.makeText(this, "Sesión inválida", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        lifecycleScope.launch {
            val list = withContext(Dispatchers.IO) {
                when (role) {
                    UserRole.CLIENT -> appointmentDao.getByClient(userId)
                    UserRole.PROVIDER -> appointmentDao.getByProvider(userId)
                    else -> emptyList()
                }
            }
            render(list, role)
        }
    }

    private fun render(items: List<AppointmentEntity>, role: UserRole) {
        container.removeAllViews()

        if (items.isEmpty()) {
            container.addView(TextView(this).apply { text = "No hay citas." })
            return
        }

        items.forEach { appt ->
            container.addView(renderRow(appt, role))
        }
    }

    private fun renderRow(appt: AppointmentEntity, role: UserRole): LinearLayout {
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 24, 24, 24)
        }

        val title = TextView(this).apply {
            textSize = 16f
            text = "${appt.serviceName}  •  ${df.format(appt.startTimeMillis)}"
        }

        val subtitle = TextView(this).apply {
            text = when (role) {
                UserRole.CLIENT -> "Prestador: ${appt.providerName}  |  Estado: ${appt.status}"
                UserRole.PROVIDER -> "Cliente: ${appt.clientName}  |  Estado: ${appt.status}"
                else -> "Estado: ${appt.status}"
            }
        }

        val price = TextView(this).apply {
            text = "Precio: $${"%.2f".format(appt.servicePrice)}"
        }

        row.addView(title)
        row.addView(subtitle)
        row.addView(price)

        // Tap -> detalle
        row.setOnClickListener {
            startActivity(
                Intent(this, AppointmentDetailActivity::class.java)
                    .putExtra("APPOINTMENT_ID", appt.id)
            )
        }

        // Long press -> cancelar (solo cliente y agendada)
        row.setOnLongClickListener {
            if (role == UserRole.CLIENT && appt.status == "AGENDADA") {
                confirmCancel(appt.id)
                true
            } else {
                false
            }
        }

        return row
    }

    private fun confirmCancel(id: Long) {
        AlertDialog.Builder(this)
            .setTitle("Cancelar cita")
            .setMessage("¿Deseas cancelar esta cita?")
            .setPositiveButton("Sí") { _, _ ->
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) { appointmentDao.cancel(id) }
                    load()
                }
            }
            .setNegativeButton("No", null)
            .show()
    }

    override fun onResume() {
        super.onResume()
        load()
    }
}

