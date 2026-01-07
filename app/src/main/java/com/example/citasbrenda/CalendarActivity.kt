package com.example.citasbrenda

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val btnCancelar = findViewById<Button>(R.id.btnCancelar)
        val btnSeleccionar = findViewById<Button>(R.id.btnSeleccionar)

        btnCancelar.setOnClickListener {
            finish() // regresa a Home
        }

        btnSeleccionar.setOnClickListener {
            startActivity(Intent(this, ConfirmActivity::class.java))
        }
    }
}

