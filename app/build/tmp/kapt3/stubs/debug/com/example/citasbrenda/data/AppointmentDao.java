package com.example.citasbrenda.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J$\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0018\u0010\u0010\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0011\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0013\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J&\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u001bJ\"\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00a7@\u00a2\u0006\u0002\u0010\u001e\u00a8\u0006\u001f"}, d2 = {"Lcom/example/citasbrenda/data/AppointmentDao;", "", "cancel", "", "appointmentId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "complete", "getByClient", "", "Lcom/example/citasbrenda/data/AppointmentEntity;", "clientId", "getByDay", "dayStart", "dayEnd", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "id", "getByProvider", "providerId", "hasOverlap", "", "start", "end", "(JJJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "appointment", "(Lcom/example/citasbrenda/data/AppointmentEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "appointments", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface AppointmentDao {
    
    @androidx.room.Insert(onConflict = 3)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.example.citasbrenda.data.AppointmentEntity appointment, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 3)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.citasbrenda.data.AppointmentEntity> appointments, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.Long>> $completion);
    
    @androidx.room.Query(value = "\n        SELECT EXISTS(\n            SELECT 1 FROM appointments\n            WHERE providerId = :providerId\n              AND status = \'AGENDADA\'\n              AND (:start < endTimeMillis AND :end > startTimeMillis)\n        )\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object hasOverlap(long providerId, long start, long end, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @androidx.room.Query(value = "\n        SELECT * FROM appointments\n        WHERE providerId = :providerId\n        ORDER BY startTimeMillis ASC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByProvider(long providerId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.citasbrenda.data.AppointmentEntity>> $completion);
    
    @androidx.room.Query(value = "\n        SELECT * FROM appointments\n        WHERE clientId = :clientId\n        ORDER BY startTimeMillis ASC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByClient(long clientId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.citasbrenda.data.AppointmentEntity>> $completion);
    
    @androidx.room.Query(value = "\n        SELECT * FROM appointments\n        WHERE startTimeMillis >= :dayStart AND startTimeMillis < :dayEnd\n        ORDER BY startTimeMillis ASC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByDay(long dayStart, long dayEnd, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.citasbrenda.data.AppointmentEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM appointments WHERE id = :id LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.citasbrenda.data.AppointmentEntity> $completion);
    
    @androidx.room.Query(value = "UPDATE appointments SET status = \'CANCELADA\' WHERE id = :appointmentId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object cancel(long appointmentId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "UPDATE appointments SET status = \'COMPLETADA\' WHERE id = :appointmentId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object complete(long appointmentId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}