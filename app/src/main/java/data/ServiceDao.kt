package com.example.citasbrenda.data

import androidx.room.*

@Dao
interface ServiceDao {

    @Query("SELECT * FROM services ORDER BY name ASC")
    suspend fun getAll(): List<ServiceEntity>

    @Query("SELECT * FROM services WHERE isActive = 1 ORDER BY name ASC")
    suspend fun getActive(): List<ServiceEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(service: ServiceEntity): Long

    @Update
    suspend fun update(service: ServiceEntity): Int

    @Query("DELETE FROM services WHERE id = :id")
    suspend fun deleteById(id: Long): Int
}

