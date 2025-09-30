package com.example.proyecto.services

import com.example.proyecto.models.PreRegForm
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/apiusers/pre-register")  // endpoint preregistro
    suspend fun preRegister(@Body req: PreRegForm): Unit

    @POST("login")
    suspend fun login(@Body req: LoginResponse): LoginResponse

    /*
    @GET("profile")
    suspend fun getProfile(): UserProfile
    // Aquí más adelante puedes añadir login, update profile, etc.
    // @POST("apiusers/login/")
    // suspend fun login(@Body req: LoginRequest): LoginResponse
    */
}