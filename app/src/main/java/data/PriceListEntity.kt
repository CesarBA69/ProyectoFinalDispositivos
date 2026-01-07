package com.example.citasbrenda.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "price_lists")
data class PriceListEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val isActive: Boolean = true,
    val createdAtMillis: Long = System.currentTimeMillis()
)

