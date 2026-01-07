package com.example.citasbrenda

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.citasbrenda.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AppointmentDetailActivity : AppCompatActivity() {

    private val df = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment_detail)

        val appointmentId = intent.getLongExtra("appointmentId", -1L)
        if (appointmentId == -1L) {
            finish()
            return
        }

        val tvTitle = findViewById<TextView>(R.id.tvDetailTitle)
        val tvClient = findViewById<TextView>(R.id.tvDetailClient)
        val tvPhone = findViewById<TextView>(R.id.tvDetailPhone)
        val tvService = findViewById<TextView>(R.id.tvDetailService)
        val tvBranch = findViewById<TextView>(R.id.tvDetailBranch)
        val tvDate = findViewById<TextView>(R.id.tvDetailDate)
        val tvStatus = findViewById<TextView>(R.id.tvDetailStatus)
        val tvNotes = findViewById<TextView>(R.id.tvDetailNotes)

        // ✅ NUEVOS (si no existen en tu XML, te digo abajo qué hacer)
        val tvProvider = findViewById<TextView>(R.id.tvDetailProvider)
        val tvPrice = findViewById<TextView>(R.id.tvDetailPrice)

        val db = AppDatabase.get(this)
        val dao = db.appointmentDao()

        lifecycleScope.launch {
            val appt = withContext(Dispatchers.IO) { dao.getById(appointmentId) }
            if (appt == null) {
                finish()
                return@launch
            }

            tvTitle.text = "Detalle de cita #${appt.id}"
            tvClient.text = "Cliente: ${appt.clientName}"
            tvPhone.text = "Teléfono: ${appt.clientPhone ?: "No registrado"}"

            tvService.text = "Servicio: ${appt.serviceName}"
            tvBranch.text = "Sucursal: ${appt.branchName}"

            tvProvider.text = "Prestador: ${appt.providerName}"
            tvPrice.text = "Precio: $${"%.2f".format(appt.servicePrice)}"

            tvDate.text = "Fecha: ${df.format(Date(appt.startTimeMillis))}"
            tvStatus.text = "Estado: ${appt.status}"
            tvNotes.text = "Notas: ${appt.notes ?: "Sin notas"}"
        }
    }
}

