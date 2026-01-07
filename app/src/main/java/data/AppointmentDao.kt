package com.example.citasbrenda.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppointmentDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(appointment: AppointmentEntity): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAll(appointments: List<AppointmentEntity>): List<Long>

    // ✅ Validación: no empalmar citas del prestador (solo contra AGENDADAS)
    @Query("""
        SELECT EXISTS(
            SELECT 1 FROM appointments
            WHERE providerId = :providerId
              AND status = 'AGENDADA'
              AND (:start < endTimeMillis AND :end > startTimeMillis)
        )
    """)
    suspend fun hasOverlap(providerId: Long, start: Long, end: Long): Boolean

    // Citas del PRESTADOR
    @Query("""
        SELECT * FROM appointments
        WHERE providerId = :providerId
        ORDER BY startTimeMillis ASC
    """)
    suspend fun getByProvider(providerId: Long): List<AppointmentEntity>

    // Citas del CLIENTE
    @Query("""
        SELECT * FROM appointments
        WHERE clientId = :clientId
        ORDER BY startTimeMillis ASC
    """)
    suspend fun getByClient(clientId: Long): List<AppointmentEntity>

    // ✅ Citas por día (para agenda visual)
    @Query("""
        SELECT * FROM appointments
        WHERE startTimeMillis >= :dayStart AND startTimeMillis < :dayEnd
        ORDER BY startTimeMillis ASC
    """)
    suspend fun getByDay(dayStart: Long, dayEnd: Long): List<AppointmentEntity>

    // Cita por ID
    @Query("SELECT * FROM appointments WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): AppointmentEntity?

    // Cancelar cita
    @Query("UPDATE appointments SET status = 'CANCELADA' WHERE id = :appointmentId")
    suspend fun cancel(appointmentId: Long): Int

    @Query("UPDATE appointments SET status = 'COMPLETADA' WHERE id = :appointmentId")
    suspend fun complete(appointmentId: Long): Int

}



