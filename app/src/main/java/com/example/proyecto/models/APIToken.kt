package com.example.proyecto.models

// Este es el response cuando verificas OTP y te devuelve el token
data class APIToken(
    val access: String,
    val refresh: String
)
