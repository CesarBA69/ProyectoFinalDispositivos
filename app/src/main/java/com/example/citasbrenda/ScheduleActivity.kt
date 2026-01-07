package com.example.citasbrenda

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.citasbrenda.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ScheduleActivity : AppCompatActivity() {

    private val df = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    private var selectedTimeMillis: Long? = null

    // ✅ Duración fija (minutos). Si luego agregas duración a ServiceEntity, aquí lo conectas.
    private val defaultDurationMinutes = 60

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        val spService = findViewById<Spinner>(R.id.spService)
        val spProvider = findViewById<Spinner>(R.id.spProvider)
        val tvPrice = findViewById<TextView>(R.id.tvPrice)
        val btnPickDateTime = findViewById<Button>(R.id.btnPickDateTime)
        val tvSelectedDateTime = findViewById<TextView>(R.id.tvSelectedDateTime)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etNotes = findViewById<EditText>(R.id.etNotes)
        val btnSave = findViewById<Button>(R.id.btnSaveAppointment)

        val session = SessionManager(this)
        val userId = session.getUserId()
        val role = session.getRole()

        if (userId == -1L || role != UserRole.CLIENT) {
            Toast.makeText(this, "Solo el cliente puede agendar", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val db = AppDatabase.get(this)
        val serviceDao = db.serviceDao()
        val userDao = db.userDao()
        val priceDao = db.priceListDao()
        val appointmentDao = db.appointmentDao()

        // Datos para spinners
        var services: List<ServiceEntity> = emptyList()
        var providers: List<UserEntity> = emptyList()
        var activeList: PriceListEntity? = null
        var currentPrice: Double? = null

        // 1) Cargar servicios + prestadores + lista activa
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                val list = priceDao.getActiveList()
                val svc = serviceDao.getActive()
                val prov = userDao.getByRole(UserRole.PROVIDER).filter { it.isActive }
                Triple(list, svc, prov)
            }

            activeList = result.first
            services = result.second
            providers = result.third

            if (activeList == null) {
                Toast.makeText(
                    this@ScheduleActivity,
                    "No hay lista de precio activa. Activa una en Admin → Listas de precio.",
                    Toast.LENGTH_LONG
                ).show()
                finish()
                return@launch
            }

            if (services.isEmpty()) {
                Toast.makeText(this@ScheduleActivity, "No hay servicios activos", Toast.LENGTH_SHORT).show()
                finish()
                return@launch
            }

            if (providers.isEmpty()) {
                Toast.makeText(this@ScheduleActivity, "No hay prestadores activos", Toast.LENGTH_SHORT).show()
                finish()
                return@launch
            }

            // Spinner servicios
            spService.adapter = ArrayAdapter(
                this@ScheduleActivity,
                android.R.layout.simple_spinner_dropdown_item,
                services.map { it.name }
            )

            // Spinner prestadores
            spProvider.adapter = ArrayAdapter(
                this@ScheduleActivity,
                android.R.layout.simple_spinner_dropdown_item,
                providers.map { it.fullName }
            )

            // Precio inicial con el primer servicio
            updatePrice(
                priceDao = priceDao,
                activeListId = activeList!!.id,
                serviceId = services[0].id,
                tvPrice = tvPrice
            ) { p -> currentPrice = p }
        }

        // 2) Cambiar precio cuando cambia el servicio
        spService.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val list = activeList ?: return
                val service = services.getOrNull(position) ?: return

                lifecycleScope.launch {
                    updatePrice(
                        priceDao = priceDao,
                        activeListId = list.id,
                        serviceId = service.id,
                        tvPrice = tvPrice
                    ) { p -> currentPrice = p }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // 3) Elegir fecha/hora
        btnPickDateTime.setOnClickListener {
            pickDateTime { millis ->
                selectedTimeMillis = millis
                tvSelectedDateTime.text = "Fecha/Hora: ${df.format(millis)}"
            }
        }

        // 4) Guardar cita con validaciones
        btnSave.setOnClickListener {
            val timeMillis = selectedTimeMillis
            if (timeMillis == null) {
                Toast.makeText(this, "Selecciona fecha y hora", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val serviceIndex = spService.selectedItemPosition
            val providerIndex = spProvider.selectedItemPosition

            val service = services.getOrNull(serviceIndex)
            val provider = providers.getOrNull(providerIndex)

            if (service == null || provider == null) {
                Toast.makeText(this, "Selecciona servicio y prestador", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val price = currentPrice
            if (price == null) {
                Toast.makeText(this, "Ese servicio no tiene precio en la lista activa", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val phone = etPhone.text.toString().trim().ifEmpty { null }
            val notes = etNotes.text.toString().trim().ifEmpty { null }

            lifecycleScope.launch {
                val ok = withContext(Dispatchers.IO) {
                    val client = userDao.findById(userId) ?: return@withContext false

                    // ✅ No agendar en el pasado
                    if (timeMillis < System.currentTimeMillis()) return@withContext false

                    // ✅ Duración fija
                    val endMillis = timeMillis + defaultDurationMinutes * 60_000L

                    // ✅ No empalmar con citas del prestador
                    val overlap = appointmentDao.hasOverlap(provider.id, timeMillis, endMillis)
                    if (overlap) return@withContext false

                    // Por ahora sucursal fija
                    val branchName = "Sucursal principal"

                    appointmentDao.insert(
                        AppointmentEntity(
                            clientId = client.id,
                            clientName = client.fullName,
                            clientPhone = phone,

                            serviceId = service.id,
                            serviceName = service.name,
                            servicePrice = price,

                            branchName = branchName,

                            providerId = provider.id,
                            providerName = provider.fullName,

                            startTimeMillis = timeMillis,
                            endTimeMillis = endMillis,

                            notes = notes,
                            status = "AGENDADA"
                        )
                    )
                    true
                }

                if (ok) {
                    Toast.makeText(this@ScheduleActivity, "Cita agendada ✅", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(
                        this@ScheduleActivity,
                        "No se pudo agendar (horario ocupado / pasado / datos inválidos)",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private suspend fun updatePrice(
        priceDao: PriceListDao,
        activeListId: Long,
        serviceId: Long,
        tvPrice: TextView,
        onPrice: (Double?) -> Unit
    ) {
        val price = withContext(Dispatchers.IO) {
            priceDao.getPrice(activeListId, serviceId)
        }
        onPrice(price)
        tvPrice.text = if (price == null) {
            "Precio: (sin precio)"
        } else {
            "Precio: $${"%.2f".format(price)}"
        }
    }

    private fun pickDateTime(onPicked: (Long) -> Unit) {
        val now = Calendar.getInstance()

        DatePickerDialog(
            this,
            { _, year, month, day ->
                val cal = Calendar.getInstance()
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, day)

                TimePickerDialog(
                    this,
                    { _, hour, minute ->
                        cal.set(Calendar.HOUR_OF_DAY, hour)
                        cal.set(Calendar.MINUTE, minute)
                        cal.set(Calendar.SECOND, 0)
                        cal.set(Calendar.MILLISECOND, 0)
                        onPicked(cal.timeInMillis)
                    },
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    true
                ).show()
            },
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}


