# 📅 CitasBrenda – Proyecto Final Android

Aplicación móvil desarrollada en **Android (Kotlin)** para la gestión de citas de un negocio de **Uñas y Pestañas**, permitiendo administrar servicios, listas de precios y agendar citas según el rol del usuario.

Proyecto realizado como **Proyecto Final** de la materia **Programación de Dispositivos Móviles**.

---

## 👥 Roles del sistema

La aplicación maneja tres tipos de usuarios:

### 🔹 ADMIN
- Gestiona servicios (crear, editar, eliminar)
- Gestiona prestadores (activar / desactivar)
- Crea y activa listas de precios
- Define precios por servicio

### 🔹 PRESTADOR
- Visualiza sus citas agendadas
- Consulta detalle de cada cita

### 🔹 CLIENTE
- Visualiza servicios disponibles
- Consulta precios desde la lista activa
- Agenda citas seleccionando:
  - Servicio
  - Prestador
  - Fecha y hora
- Visualiza y cancela sus citas

---

## ⚙️ Funcionalidades principales

- Registro e inicio de sesión
- Control de acceso por roles
- Gestión de servicios
- Gestión de prestadores
- Listas de precios dinámicas
- Agenda de citas
- Persistencia de datos con **Room**
- Uso de **Coroutines** para operaciones en segundo plano
- Interfaz basada en XML

---

## 🧠 Lógica importante del sistema

- Al agendar una cita se **congela la información**:
  - Nombre del servicio
  - Precio del servicio
  - Nombre del prestador  

  Esto garantiza que:
  - Si el precio cambia después, la cita NO se vea afectada
  - Si el servicio se edita, la cita mantiene su información original

---

## 🗄️ Tecnologías utilizadas

- **Kotlin**
- **Android SDK**
- **Room Database**
- **Coroutines**
- **XML Layouts**
- **Git & GitHub**

---

## 🚀 Cómo ejecutar el proyecto

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/CesarBA69/ProyectoFinalDispositivos.git

2. Una vez ejecutado el proyecto usar los siguientes usuarios para acceder a los distintos perfiles 

ADMIN
Correo: admin@brenda.com
Contraseña: 123456

PRESTADOR
Correo: prestador@brenda.com
Contraseña: 123456

CLIENTE

Correo: cliente@brenda.com
Contraseña: 123456

Se puede cambiar contraseña en caso de ser requerido y el Administrador es el unico que puede añadir y quitar un PRESTADOR, el cliente es libre de crear su propia cuenta  
