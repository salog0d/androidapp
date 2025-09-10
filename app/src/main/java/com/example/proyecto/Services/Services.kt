package com.example.proyecto.Services

interface QuotesService {

    @GET("quotes")                 // ← CAMBIA si tu endpoint es otro
    suspend fun getQuotes() // ← CAMBIA el tipo si tu JSON es distinto

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
            .create<QuotesService>()
    }
}