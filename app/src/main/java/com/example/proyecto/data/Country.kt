package com.example.proyecto.data // Or your chosen package for data models

data class Country(
    val name: Name,
    val idd: Idd
)

data class Name(
    val common: String,
    val official: String
    // Add other name fields if needed, based on the API response
)

data class Idd(
    val root: String?,
    val suffixes: List<String>?
    // Add other idd fields if needed, based on the API response
)