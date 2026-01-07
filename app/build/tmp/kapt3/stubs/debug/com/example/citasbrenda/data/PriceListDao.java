package com.example.citasbrenda.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00132\u0006\u0010\r\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ \u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001aH\u00a7@\u00a2\u0006\u0002\u0010\u001bJ\u0016\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u001eJ\u001e\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0017H\u00a7@\u00a2\u0006\u0002\u0010!J\u0016\u0010\"\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u001e\u00a8\u0006#"}, d2 = {"Lcom/example/citasbrenda/data/PriceListDao;", "", "deactivateAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteItem", "itemId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteList", "id", "existsItem", "", "priceListId", "serviceId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveList", "Lcom/example/citasbrenda/data/PriceListEntity;", "getAllLists", "", "getItems", "Lcom/example/citasbrenda/data/PriceItemWithService;", "getPrice", "", "insertItem", "item", "Lcom/example/citasbrenda/data/PriceItemEntity;", "(Lcom/example/citasbrenda/data/PriceItemEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertList", "list", "(Lcom/example/citasbrenda/data/PriceListEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateItemPrice", "price", "(JDLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateList", "app_debug"})
@androidx.room.Dao()
public abstract interface PriceListDao {
    
    @androidx.room.Query(value = "SELECT * FROM price_lists ORDER BY createdAtMillis DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllLists(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.citasbrenda.data.PriceListEntity>> $completion);
    
    @androidx.room.Insert(onConflict = 3)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertList(@org.jetbrains.annotations.NotNull()
    com.example.citasbrenda.data.PriceListEntity list, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateList(@org.jetbrains.annotations.NotNull()
    com.example.citasbrenda.data.PriceListEntity list, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "DELETE FROM price_lists WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteList(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "UPDATE price_lists SET isActive = 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deactivateAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "\n        SELECT \n            pi.id AS itemId,\n            pi.priceListId AS priceListId,\n            pi.serviceId AS serviceId,\n            s.name AS serviceName,\n            pi.price AS price\n        FROM price_items pi\n        INNER JOIN services s ON s.id = pi.serviceId\n        WHERE pi.priceListId = :priceListId\n        ORDER BY s.name ASC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getItems(long priceListId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.citasbrenda.data.PriceItemWithService>> $completion);
    
    @androidx.room.Insert(onConflict = 3)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertItem(@org.jetbrains.annotations.NotNull()
    com.example.citasbrenda.data.PriceItemEntity item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "UPDATE price_items SET price = :price WHERE id = :itemId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateItemPrice(long itemId, double price, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "DELETE FROM price_items WHERE id = :itemId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteItem(long itemId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM price_lists WHERE isActive = 1 LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getActiveList(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.citasbrenda.data.PriceListEntity> $completion);
    
    @androidx.room.Query(value = "\n        SELECT price\n        FROM price_items\n        WHERE priceListId = :priceListId AND serviceId = :serviceId\n        LIMIT 1\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPrice(long priceListId, long serviceId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "\n        SELECT EXISTS(\n            SELECT 1 FROM price_items\n            WHERE priceListId = :priceListId AND serviceId = :serviceId\n        )\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object existsItem(long priceListId, long serviceId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
}