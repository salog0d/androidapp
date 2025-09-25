// data/Networking.kt
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Networking {
    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        .build()

    val api: RestCountriesApi = Retrofit.Builder()
        .baseUrl("https://restcountries.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RestCountriesApi::class.java)
}
