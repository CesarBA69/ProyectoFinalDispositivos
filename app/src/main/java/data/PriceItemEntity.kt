package com.example.citasbrenda.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "price_items",
    foreignKeys = [
        ForeignKey(
            entity = PriceListEntity::class,
            parentColumns = ["id"],
            childColumns = ["priceListId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ServiceEntity::class,
            parentColumns = ["id"],
            childColumns = ["serviceId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index("priceListId"),
        Index("serviceId"),
        Index(value = ["priceListId", "serviceId"], unique = true)
    ]
)
data class PriceItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val priceListId: Long,
    val serviceId: Long,
    val price: Double
)
