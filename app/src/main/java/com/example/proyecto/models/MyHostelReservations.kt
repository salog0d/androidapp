package com.example.proyecto.models

data class MyHostelReservations(
    val arrival_date: String,
    val created_at: String,
    val created_by_name: String,
    val hostel: String,
    val hostel_location: String,
    val hostel_name: String,
    val id: String,
    val men_quantity: Int,
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