package com.example.proyecto.models

data class PreRegForm(
    val first_name: String,              // Nombre, string, obligatorio
    val last_name: String,               // Apellido, string, obligatorio
    val phone_number: String,            // Teléfono, string con regex ^\+?\d{10,15}$
    val age: Int,                        // Edad, entero, mínimo 0
    val gender: String,                  // Género, "M" o "F"
    val privacy_policy_accepted: Boolean // Aceptación de políticas, boolean
)

data class PreRegResponse(
    val message: String,
    val registrationId: String? // Ejemplo de un dato que podría devolver la API
)