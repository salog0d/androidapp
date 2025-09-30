package com.example.proyecto.models

data class Service(
    val created_at: String,
    val description: String,
    val id: String,
    val is_active: Boolean,
    val max_time: Int,
    val name: String,
    val needs_approval: Boolean,
    val price: Int,
    val reservation_type: String
)