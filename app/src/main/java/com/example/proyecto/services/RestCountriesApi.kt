package com.example.proyecto.services

import com.example.proyecto.models.Country
import retrofit2.http.GET

interface RestCountriesApi {
    @GET("v3.1/all?fields=name,idd")
    suspend fun getAll(): List<Country>
}
