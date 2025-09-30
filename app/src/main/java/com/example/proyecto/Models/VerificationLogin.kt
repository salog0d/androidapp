package com.example.proyecto.Models


sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}
data class VerificationLogin(
    val phone_number: String
)