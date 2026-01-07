package com.example.citasbrenda.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointments")
data class AppointmentEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,

    val clientId: Long,
    val clientName: String,
    val clientPhone: String? = null,

    // ✅ Snapshot (congelado)
    val serviceId: Long,
    val serviceName: String,
    val servicePrice: Double,

    val branchName: String,

    // ✅ Snapshot (congelado)
    val providerId: Long,
    val providerName: String,

    // ✅ Rango de tiempo
    val startTimeMillis: Long,
    val endTimeMillis: Long,

    val notes: String? = null,
    val status: String = "AGENDADA"
)

