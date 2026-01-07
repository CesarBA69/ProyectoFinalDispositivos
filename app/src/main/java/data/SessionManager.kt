package com.example.citasbrenda.data

import android.content.Context

class SessionManager(context: Context) {

    private val prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE)

    fun saveUser(userId: Long, role: UserRole) {
        prefs.edit()
            .putLong("userId", userId)
            .putString("role", role.name)
            .apply()
    }

    fun getUserId(): Long = prefs.getLong("userId", -1)
    fun getRole(): UserRole? = prefs.getString("role", null)?.let { UserRole.valueOf(it) }

    fun clear() {
        prefs.edit().clear().apply()
    }
}
