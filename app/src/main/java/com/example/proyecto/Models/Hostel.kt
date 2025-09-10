package com.example.proyecto.Models

data class Hostel(
    val created_at: String,
    val id: String,
    val is_active: Boolean,
    val location: Location,
    val men_capacity: Int,
    val name: String,
    val phone: String,
    val women_capacity: Int
)