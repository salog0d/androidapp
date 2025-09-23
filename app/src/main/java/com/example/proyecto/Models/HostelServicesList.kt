package com.example.proyecto.Models

data class HostelServicesList(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<HostelServices>
)