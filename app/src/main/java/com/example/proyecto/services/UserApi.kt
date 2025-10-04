package com.example.proyecto.services

import com.example.proyecto.models.PreRegForm
import retrofit2.http.Body
import retrofit2.http.POST
import com.example.proyecto.models.AdminLoginRequest
import com.example.proyecto.models.PhoneVerificationRequest
import com.example.proyecto.models.TokenResponse
import com.example.proyecto.models.LoginResponse

interface UserApi {
    @POST("/api/users/pre-register")  // endpoint preregistro
    suspend fun preRegister(@Body req: PreRegForm): Unit

    @POST("login")
    suspend fun login(@Body req: LoginResponse): LoginResponse

    @POST("api/users/auth/admin-login/")
    suspend fun adminLogin(@Body req: AdminLoginRequest): TokenResponse

    @POST("api/users/phone-verification/verify/")
    suspend fun userLogin(@Body req: PhoneVerificationRequest): TokenResponse
}

    /*
    @GET("profile")
    suspend fun getProfile(): UserProfile
    // Aquí más adelante puedes añadir login, update profile, etc.
    // @POST("apiusers/login/")
    // suspend fun login(@Body req: LoginRequest): LoginResponse
    */
