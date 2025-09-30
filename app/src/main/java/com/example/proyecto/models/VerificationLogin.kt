package com.example.proyecto.models


sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}
data class VerificationLogin(
    val phone_number: String
)