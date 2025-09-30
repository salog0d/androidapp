package com.example.proyecto.models

data class VerificationOTP(
    val code: String,
    val phone_number: String
)