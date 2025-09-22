package com.example.proyecto.Services

import android.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


data class Login(
    val phone: String
)

data class LoginResponse(
    val token: String // or whatever your API returns
)

data class  OTPcode(
    val OTP: String
)

data class APIToken(
    val token: String
)
interface Services {

    @GET("quotes")
    suspend fun getQuotes()

    @POST("Login")
    @Headers("No-Auth: true")
    suspend fun verifylogin(@Body request: Login): LoginResponse

    @POST("OTP")
    @Headers("No-Auth: true")
    suspend  fun verifyotp(@Body request: OTPcode): APIToken

    companion object {
        private val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        private val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val original = chain.request()

                val requestBuilder = original.newBuilder()

                // Only add Authorization if "No-Auth" header is NOT present
                if (original.header("No-Auth") == null) {
                    val token = com.example.proyecto.Models.MyApp.tokenManager.getToken()
                    if (token != null) {
                        requestBuilder.addHeader("Authorization", "Token $token")
                    }
                } else {
                    // Remove the No-Auth marker before sending
                    requestBuilder.removeHeader("No-Auth")
                }

                chain.proceed(requestBuilder.build())
            }
            .build()

        val instance: Services = Retrofit.Builder()
            .baseUrl("https://zenquotes.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(Services::class.java)
    }

}