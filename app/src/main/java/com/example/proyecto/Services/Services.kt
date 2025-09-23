package com.example.proyecto.Services

import android.R
import com.example.proyecto.Models.HostelList
import com.example.proyecto.Models.HostelServicesList
import com.example.proyecto.Models.MyHostelReservationList
import com.example.proyecto.Models.MyServiceReservationList
import com.example.proyecto.Models.NewServiceReservation
import com.example.proyecto.Models.NewHostelReservation
import com.example.proyecto.Models.VerificationLogin
import com.example.proyecto.Models.VerificationOTP
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


data class LoginResponse(
    val token: String // or whatever your API returns
)

data class APIToken(
    val token: String
)
interface Services {

    // Send phone number for user verification and receive OTP
    @POST("/apiusers/phone-verification/send/")
    @Headers("No-Auth: true")
    suspend fun verifyLogin(@Body request: VerificationLogin): LoginResponse

    // Verify OTP
    @POST("/apiusers/phone-verification/verify/")
    @Headers("No-Auth: true")
    suspend  fun verifyOtp(@Body request: VerificationOTP): APIToken

    // Fetch hostels
    @GET("/apialbergues/hostels/")
    @Headers("No-Auth: false")
    suspend fun getHostels(): HostelList

    // Fetch user's hostel reservations
    @GET("/apialbergues/reservations/my_reservations/")
    @Headers("No-Auth: false")
    suspend fun getMyReservations(): MyHostelReservationList

    //Create a new hostel reservation
    @POST("/apialbergues/reservations/")
    @Headers("No-Auth: false")
    suspend fun createHostelReservation(@Body request: NewHostelReservation): NewHostelReservation

    // Fetch Hostel Services
    @GET("/apiservices/hostel-services/")
    @Headers("No-Auth: false")
    suspend fun getHostelServices(): HostelServicesList

    // Fetch user's service reservations
    @GET("/apiservices/reservations/my_reservations/")
    @Headers("No-Auth: false")
    suspend fun getMyServiceReservations(): MyServiceReservationList

    // Fetch user's upcoming service reservations 24 hours
    @GET("/apiservices/reservations/upcoming/")
    @Headers("No-Auth: false")
    suspend fun getMyUpcomingServiceReservations(): MyServiceReservationList

    //Create a new service reservation
    @POST("/apiservices/reservations/")
    @Headers("No-Auth: false")
    suspend fun createServiceReservation(@Body request: NewServiceReservation): NewServiceReservation



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
            .baseUrl("https://localhost:8000") // Replace with your API base URL
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(Services::class.java)
    }

}