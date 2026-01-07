package com.example.citasbrenda.data

import android.content.Context

object DatabaseSeeder {

    private const val PREFS_NAME = "seed_prefs"
    private const val KEY_SEEDED = "db_seeded_v1"

    suspend fun seedIfNeeded(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        if (prefs.getBoolean(KEY_SEEDED, false)) return

        val db = AppDatabase.get(context)
        val userDao = db.userDao()
        val appointmentDao = db.appointmentDao()

        // Usuarios de prueba
        userDao.insert(
            UserEntity(
                fullName = "Admin Brenda",
                email = "admin@brenda.com",
                passwordHash = Security.sha256("123456"),
                role = UserRole.ADMIN
            )
        )

        val providerId = userDao.insert(
            UserEntity(
                fullName = "Prestador Brenda",
                email = "prestador@brenda.com",
                passwordHash = Security.sha256("123456"),
                role = UserRole.PROVIDER
            )
        )

        val clientId = userDao.insert(
            UserEntity(
                fullName = "Cliente Brenda",
                email = "cliente@brenda.com",
                passwordHash = Security.sha256("123456"),
                role = UserRole.CLIENT
            )
        )

        // Citas de prueba
        val now = System.currentTimeMillis()
        val durationMillis = 60 * 60 * 1000L //  60 min

        val start1 = now + 60 * 60 * 1000L
        val start2 = now + 24 * 60 * 60 * 1000L

        val citas = listOf(
            AppointmentEntity(
                clientId = clientId,
                clientName = "Cliente Brenda",
                clientPhone = "555-123-4567",

                serviceId = 1L,
                serviceName = "Uñas acrílicas",
                servicePrice = 350.0,

                branchName = "Sucursal Centro",

                providerId = providerId,
                providerName = "Prestador Brenda",

                startTimeMillis = start1,
                endTimeMillis = start1 + durationMillis, //  agregado
                notes = "Traer diseño de referencia",
                status = "AGENDADA"
            ),
            AppointmentEntity(
                clientId = clientId,
                clientName = "Cliente Brenda",
                clientPhone = "555-123-4567",

                serviceId = 2L,
                serviceName = "Pestañas volumen",
                servicePrice = 500.0,

                branchName = "Sucursal Norte",

                providerId = providerId,
                providerName = "Prestador Brenda",

                startTimeMillis = start2,
                endTimeMillis = start2 + durationMillis, //  agregado
                notes = "Primera vez",
                status = "AGENDADA"
            )
        )

        appointmentDao.insertAll(citas)

        prefs.edit().putBoolean(KEY_SEEDED, true).apply()
    }
}


