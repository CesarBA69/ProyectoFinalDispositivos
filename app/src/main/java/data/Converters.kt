package com.example.citasbrenda.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter fun fromRole(role: UserRole): String = role.name
    @TypeConverter fun toRole(value: String): UserRole = UserRole.valueOf(value)
}
