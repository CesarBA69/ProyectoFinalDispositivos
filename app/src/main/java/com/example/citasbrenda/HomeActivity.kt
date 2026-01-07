package com.example.citasbrenda

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.citasbrenda.data.SessionManager
import com.example.citasbrenda.data.UserRole

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        findViewById<Button>(R.id.btnChangePassword).setOnClickListener {
            startActivity(Intent(this, ChangePasswordActivity::class.java))
        }

        val session = SessionManager(this)
        val role = session.getRole()
        val userId = session.getUserId()

        if (role == null || userId == -1L) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        val btnCalendario = findViewById<Button>(R.id.btnCalendario)
        val btnMisCitas = findViewById<Button>(R.id.btnMisCitas)
        val btnAgendar = findViewById<Button>(R.id.btnAgendar)

        when (role) {
            UserRole.ADMIN -> {
                // Admin: solo panel
                btnCalendario.visibility = View.GONE
                btnMisCitas.visibility = View.GONE

                btnAgendar.text = "⚙️ Panel administrador"
                btnAgendar.setOnClickListener {
                    startActivity(Intent(this, AdminDashboardActivity::class.java))
                }
            }

            UserRole.PROVIDER -> {
                // Prestador: ver agenda del día + citas
                btnAgendar.visibility = View.GONE

                btnCalendario.text = "📅 Agenda del día"
                btnCalendario.setOnClickListener {
                    startActivity(Intent(this, DailyAgendaActivity::class.java))
                }

                btnMisCitas.text = "📋 Citas agendadas"
                btnMisCitas.setOnClickListener {
                    startActivity(Intent(this, MyAppointmentsActivity::class.java))
                }
            }

            UserRole.CLIENT -> {
                // Cliente: agenda del día + mis citas + agendar
                btnCalendario.text = "📅 Agenda del día"
                btnCalendario.setOnClickListener {
                    startActivity(Intent(this, DailyAgendaActivity::class.java))
                }

                btnMisCitas.text = "📋 Mis citas"
                btnMisCitas.setOnClickListener {
                    startActivity(Intent(this, MyAppointmentsActivity::class.java))
                }

                btnAgendar.text = "✅ Agendar cita"
                btnAgendar.setOnClickListener {
                    startActivity(Intent(this, ScheduleActivity::class.java))
                }
            }
        }
    }
}



