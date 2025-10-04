package com.example.proyecto.models

// Este es el response cuando mandas el número para pedir el OTP
data class LoginResponse(
    val success: Boolean? = null,
    val message: String? = null
)
