package com.example.citasbrenda.data

import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: UserEntity): Long

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun findByEmail(email: String): UserEntity?

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    suspend fun findById(id: Long): UserEntity?

    @Query("UPDATE users SET passwordHash = :newHash WHERE id = :userId")
    suspend fun updatePassword(userId: Long, newHash: String): Int

    // ✅ NUEVO: listar prestadores
    @Query("SELECT * FROM users WHERE role = :role ORDER BY fullName ASC")
    suspend fun getByRole(role: UserRole): List<UserEntity>

    // ✅ NUEVO: activar/desactivar
    @Query("UPDATE users SET isActive = :active WHERE id = :userId")
    suspend fun setActive(userId: Long, active: Boolean): Int

    // ✅ NUEVO: editar datos
    @Query("UPDATE users SET fullName = :fullName, email = :email WHERE id = :userId")
    suspend fun updateBasic(userId: Long, fullName: String, email: String): Int

    // ✅ NUEVO: eliminar
    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteById(userId: Long): Int
}

