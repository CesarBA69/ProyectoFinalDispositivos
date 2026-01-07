package com.example.citasbrenda.data

import androidx.room.*

@Dao
interface BranchDao {

    @Query("SELECT * FROM branches ORDER BY name ASC")
    suspend fun getAll(): List<BranchEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(branch: BranchEntity): Long

    @Update
    suspend fun update(branch: BranchEntity): Int

    @Query("DELETE FROM branches WHERE id = :id")
    suspend fun deleteById(id: Long): Int
}
