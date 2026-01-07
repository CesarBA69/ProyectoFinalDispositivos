package com.example.citasbrenda.data

import androidx.room.*

@Dao
interface PriceListDao {

    @Query("SELECT * FROM price_lists ORDER BY createdAtMillis DESC")
    suspend fun getAllLists(): List<PriceListEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertList(list: PriceListEntity): Long

    @Update
    suspend fun updateList(list: PriceListEntity): Int

    @Query("DELETE FROM price_lists WHERE id = :id")
    suspend fun deleteList(id: Long): Int

    @Query("UPDATE price_lists SET isActive = 0")
    suspend fun deactivateAll(): Int

    @Query("""
        SELECT 
            pi.id AS itemId,
            pi.priceListId AS priceListId,
            pi.serviceId AS serviceId,
            s.name AS serviceName,
            pi.price AS price
        FROM price_items pi
        INNER JOIN services s ON s.id = pi.serviceId
        WHERE pi.priceListId = :priceListId
        ORDER BY s.name ASC
    """)
    suspend fun getItems(priceListId: Long): List<PriceItemWithService>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertItem(item: PriceItemEntity): Long

    @Query("UPDATE price_items SET price = :price WHERE id = :itemId")
    suspend fun updateItemPrice(itemId: Long, price: Double): Int

    @Query("DELETE FROM price_items WHERE id = :itemId")
    suspend fun deleteItem(itemId: Long): Int

    @Query("SELECT * FROM price_lists WHERE isActive = 1 LIMIT 1")
    suspend fun getActiveList(): PriceListEntity?

    @Query("""
        SELECT price
        FROM price_items
        WHERE priceListId = :priceListId AND serviceId = :serviceId
        LIMIT 1
    """)
    suspend fun getPrice(priceListId: Long, serviceId: Long): Double?

    // ✅ ESTE ES EL QUE TE FALTABA
    @Query("""
        SELECT EXISTS(
            SELECT 1 FROM price_items
            WHERE priceListId = :priceListId AND serviceId = :serviceId
        )
    """)
    suspend fun existsItem(priceListId: Long, serviceId: Long): Boolean
}



