package com.example.proyecto.models

data class ServiceHostel(
    val created_at: String,
    val hostel: String,
    val id: String,
    val is_active: Boolean,
    val service: Service
)