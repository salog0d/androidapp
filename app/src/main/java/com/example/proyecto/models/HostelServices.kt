package com.example.proyecto.models

data class HostelServices(
    val created_at: String,
    val created_by_name: String,
    val hostel: String,
    val hostel_location: String,
    val hostel_name: String,
    val id: String,
    val is_active: Boolean,
    val schedule: String,
    val schedule_data: HServicesScheduleData,
    val service: String,
    val service_description: String,
    val service_max_time: Int,
    val service_name: String,
    val service_needs_approval: Boolean,
    val service_price: String,
    val total_reservations: Int,
    val updated_at: String
)