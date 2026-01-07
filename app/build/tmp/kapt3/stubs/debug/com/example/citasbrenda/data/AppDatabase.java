package com.example.citasbrenda.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&\u00a8\u0006\u000e"}, d2 = {"Lcom/example/citasbrenda/data/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "appointmentDao", "Lcom/example/citasbrenda/data/AppointmentDao;", "branchDao", "Lcom/example/citasbrenda/data/BranchDao;", "priceListDao", "Lcom/example/citasbrenda/data/PriceListDao;", "serviceDao", "Lcom/example/citasbrenda/data/ServiceDao;", "userDao", "Lcom/example/citasbrenda/data/UserDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.example.citasbrenda.data.UserEntity.class, com.example.citasbrenda.data.AppointmentEntity.class, com.example.citasbrenda.data.BranchEntity.class, com.example.citasbrenda.data.ServiceEntity.class, com.example.citasbrenda.data.PriceListEntity.class, com.example.citasbrenda.data.PriceItemEntity.class}, version = 7, exportSchema = false)
@androidx.room.TypeConverters(value = {com.example.citasbrenda.data.Converters.class})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.example.citasbrenda.data.AppDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.citasbrenda.data.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.citasbrenda.data.UserDao userDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.citasbrenda.data.AppointmentDao appointmentDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.citasbrenda.data.BranchDao branchDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.citasbrenda.data.ServiceDao serviceDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.citasbrenda.data.PriceListDao priceListDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/example/citasbrenda/data/AppDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/example/citasbrenda/data/AppDatabase;", "get", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.citasbrenda.data.AppDatabase get(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}