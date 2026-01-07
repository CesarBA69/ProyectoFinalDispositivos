package com.example.citasbrenda

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.citasbrenda.data.AppDatabase
import com.example.citasbrenda.data.AppointmentEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DailyAgendaActivity : AppCompatActivity() {

    private val dfDay = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val dfTime = SimpleDateFormat("HH:mm", Locale.getDefault())

    private lateinit var tvDay: TextView
    private lateinit var container: LinearLayout

    private val db by lazy { AppDatabase.get(this) }
    private val appointmentDao by lazy { db.appointmentDao() }

    // Horario fijo (puedes cambiarlo)
    private val startHour = 9
    private val endHour = 18
    private val slotMinutes = 30

    private var dayStartMillis: Long? = null
    private var dayEndMillis: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_agenda)

        tvDay = findViewById(R.id.tvDay)
        container = findViewById(R.id.containerSlots)

        findViewById<Button>(R.id.btnPickDay).setOnClickListener {
            pickDay()
        }

        // Por default: hoy
        val now = Calendar.getInstance()
        setDay(now)
    }

    private fun pickDay() {
        val cal = Calendar.getInstance()

        DatePickerDialog(
            this,
            { _, year, month, day ->
                val c = Calendar.getInstance()
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, month)
                c.set(Calendar.DAY_OF_MONTH, day)
                setDay(c)
            },
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun setDay(dayCal: Calendar) {
        val dayStart = dayCal.clone() as Calendar
        dayStart.set(Calendar.HOUR_OF_DAY, 0)
        dayStart.set(Calendar.MINUTE, 0)
        dayStart.set(Calendar.SECOND, 0)
        dayStart.set(Calendar.MILLISECOND, 0)

        val dayEnd = dayStart.clone() as Calendar
        dayEnd.add(Calendar.DAY_OF_MONTH, 1)

        dayStartMillis = dayStart.timeInMillis
        dayEndMillis = dayEnd.timeInMillis

        tvDay.text = "Día: ${dfDay.format(dayStart.time)}"
        loadAgenda()
    }

    private fun loadAgenda() {
        val ds = dayStartMillis ?: return
        val de = dayEndMillis ?: return

        lifecycleScope.launch {
            val appointments = withContext(Dispatchers.IO) {
                appointmentDao.getByDay(ds, de)
            }

            renderSlots(appointments)
        }
    }

    private fun renderSlots(appointments: List<AppointmentEntity>) {
        container.removeAllViews()

        // Generar slots del día (9:00 a 18:00 cada 30 min)
        val base = Calendar.getInstance().apply {
            timeInMillis = dayStartMillis!!
        }

        // Arrancar a startHour
        base.set(Calendar.HOUR_OF_DAY, startHour)
        base.set(Calendar.MINUTE, 0)
        base.set(Calendar.SECOND, 0)
        base.set(Calendar.MILLISECOND, 0)

        val end = (base.clone() as Calendar).apply {
            set(Calendar.HOUR_OF_DAY, endHour)
            set(Calendar.MINUTE, 0)
        }

        while (base.before(end)) {
            val slotStart = base.timeInMillis
            val slotEnd = slotStart + slotMinutes * 60_000L

            val appt = appointments.firstOrNull { a ->
                // Ocupado si hay traslape con el slot
                a.status == "AGENDADA" && slotStart < a.endTimeMillis && slotEnd > a.startTimeMillis
            }

            val tv = TextView(this).apply {
                textSize = 16f
                setPadding(16, 16, 16, 16)

                text = if (appt == null) {
                    "🟢 ${dfTime.format(slotStart)} - ${dfTime.format(slotEnd)}  |  Disponible"
                } else {
                    "🔴 ${dfTime.format(slotStart)} - ${dfTime.format(slotEnd)}  |  " +
                            "${appt.serviceName}  (${appt.providerName})"
                }

                setOnClickListener {
                    if (appt == null) {
                        Toast.makeText(
                            this@DailyAgendaActivity,
                            "Disponible: ${dfTime.format(slotStart)}",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@DailyAgendaActivity,
                            "Cita: ${appt.serviceName} - ${appt.clientName}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            container.addView(tv)

            // siguiente slot
            base.add(Calendar.MINUTE, slotMinutes)
        }

        if (container.childCount == 0) {
            container.addView(TextView(this).apply {
                text = "No hay horarios configurados."
            })
        }
    }
}
