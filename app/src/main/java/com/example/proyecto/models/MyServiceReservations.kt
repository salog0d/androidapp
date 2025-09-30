package com.example.proyecto.models

data class MyServiceReservations(
    val created_at: String,
    val created_by_name: String,
    val datetime_reserved: String,
    val duration_minutes: Int,
    val end_datetime_reserved: String,
    val hostel_location: String,
    val hostel_name: String,
    val id: String,
    val is_expired: Boolean,
    val men_quantity: Int,
    val service: String,
    val service_name: String,
    val service_price: String,
    val status: String,
    val status_display: String,
    val total_people: Int,
    val type: String,
    val type_display: String,
    val updated_at: String,
    val user: String,
    val user_name: String,
    val user_phone: String,
    val women_quantity: Int
)