package com.example.citasbrenda

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class AppointmentItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val tvTitle = TextView(context)
    private val tvSubtitle = TextView(context)
    private val tvDate = TextView(context)
    private val tvStatus = TextView(context)
    private val btnCancel = Button(context)

    init {
        orientation = VERTICAL
        setPadding(20, 20, 20, 20)

        tvTitle.textSize = 16f
        tvSubtitle.textSize = 14f
        tvDate.textSize = 14f
        tvStatus.textSize = 14f

        btnCancel.text = "Cancelar cita"

        addView(tvTitle)
        addView(tvSubtitle)
        addView(tvDate)
        addView(tvStatus)
        addView(btnCancel)
    }

    fun setTitle(text: String) { tvTitle.text = text }
    fun setSubtitle(text: String) { tvSubtitle.text = text }
    fun setDate(text: String) { tvDate.text = text }
    fun setStatus(text: String) { tvStatus.text = text }

    fun setOnCancelClick(action: () -> Unit) {
        btnCancel.setOnClickListener { action() }

        fun setOnItemClick(action: () -> Unit) {
            setOnClickListener { action() }
        }

    }
}

