package com.example.proyecto.Services

import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

interface QuotesService {

    @GET("quotes")                 // ← CAMBIA si tu endpoint es otro
    suspend fun getQuotes(): List<Quote> // Assuming getQuotes returns a List of Quote objects. Adjust if necessary.
    companion object {
        private val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC // BODY solo para debug
        }
        private val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val instance: QuotesService = Retrofit.Builder()
            .baseUrl("https://zenquotes.io/api/") // ← CAMBIA la base (SIEMPRE con '/')
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(QuotesService::class.java) // Explicitly pass the class    }
    }
}


// You'll need a data class to represent the structure of a quote from the API
data class Quote(
    val q: String, // quote text
    val a: String, // author
    // Add other fields if your API returns more data
)