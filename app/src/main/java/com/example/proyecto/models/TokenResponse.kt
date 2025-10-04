package com.example.proyecto.models

data class TokenResponse(
    val token: String,
    val user: UserInfo? = null
)
