// data/RestCountriesApi.kt
import com.example.proyecto.Models.Country
import retrofit2.http.GET

interface RestCountriesApi {
    // solo lo necesario: nombre y código telefónico
    @GET("v3.1/all?fields=name,idd")
    suspend fun getAll(): List<Country>
}
