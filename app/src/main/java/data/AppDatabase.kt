package com.example.citasbrenda.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        UserEntity::class,
        AppointmentEntity::class,
        BranchEntity::class,
        ServiceEntity::class,
        PriceListEntity::class,
        PriceItemEntity::class
    ],
    version = 7,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun appointmentDao(): AppointmentDao
    abstract fun branchDao(): BranchDao
    abstract fun serviceDao(): ServiceDao
    abstract fun priceListDao(): PriceListDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun get(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "citasbrenda.db"
                )
                    // ✅ Para desarrollo: si cambia el esquema, borra y recrea la DB
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}



