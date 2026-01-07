package com.example.citasbrenda.data

data class PriceItemWithService(
    val itemId: Long,
    val priceListId: Long,
    val serviceId: Long,
    val serviceName: String,
    val price: Double
)
