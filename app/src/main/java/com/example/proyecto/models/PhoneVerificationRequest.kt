package com.example.proyecto.models

data class PhoneVerificationRequest(
    val phone_number: String,
    val code: String
)
