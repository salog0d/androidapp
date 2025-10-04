// data/Networking.kt
package com.example.proyecto.data

import com.example.proyecto.services.RestCountriesApi
import com.example.proyecto.services.Services
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Networking {

    private val logging = HttpLoggingInterceptor().setLevel (
        HttpLoggingInterceptor.Level.BASIC
    )

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor { chain ->
            val original = chain.request()
            val sendAuth = original.header("No-Auth") == "false"
            val builder = original.newBuilder().removeHeader("No-Auth")
            if (sendAuth) {
                com.example.proyecto.models.MyApp.tokenManager.getToken()?.let {
                    builder.addHeader("Authorization", "Token $it")
                }
            }
            chain.proceed(builder.build())
        }
        .build()

       private fun retrofit(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl) // debe terminar con '/'
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // Backend local (emulador AOSP usa 10.0.2.2)
    val services: Services =
        retrofit("http://20.246.91.21:8001/api/").create(Services::class.java)

    // REST Countries (sin auth)
    val countriesApi: RestCountriesApi =
        retrofit("https://restcountries.com/").create(RestCountriesApi::class.java)

    // UserApi (con auth)
    val userApi: com.example.proyecto.services.UserApi =
        retrofit("http://20.246.91.21:8001/api/").create(com.example.proyecto.services.UserApi::class.java)

}


// Old Services.kt
/*
companion object {
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor { chain ->
            val original = chain.request()
            val noAuthHeader = original.header("No-Auth")
            val requestBuilder = original.newBuilder()

            if (noAuthHeader == "false") {
                val token = com.example.proyecto.Models.MyApp.tokenManager.getToken()
                if (token != null) {
                    requestBuilder.addHeader("Authorization", "Token $token")
                }
            }
            // Remove the No-Auth header before sending
            requestBuilder.removeHeader("No-Auth")

            chain.proceed(requestBuilder.build())
        }

        .build()

    val instance: Services = Retrofit.Builder()
        .baseUrl("https://localhost:8000/api/") // Replace with your API base URL
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(Services::class.java)
}*/

