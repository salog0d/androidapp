package com.example.proyecto.Models

data class ServiceList(
    val count: Int,
    val next: String,
    val previous: String,
    val serviceHostels: List<ServiceHostel>
)