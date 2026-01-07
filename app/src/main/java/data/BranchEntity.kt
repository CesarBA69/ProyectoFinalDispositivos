package com.example.citasbrenda.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "branches",
    indices = [Index(value = ["name"], unique = true)]
)
data class BranchEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val address: String? = null,
    val isActive: Boolean = true
)
