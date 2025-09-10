package com.example.proyecto.Models

data class Location(
    val address: String,
    val city: String,
    val country: String,
    val id: String,
    val landmarks: String,
    val latitude: Int,
    val longitude: Int,
    val state: String,
    val zip_code: String
)