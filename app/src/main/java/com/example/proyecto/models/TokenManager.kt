package com.example.proyecto.models

import android.app.Application
import com.example.proyecto.utilities.TokenManager

class MyApp : Application() {
    companion object {
        lateinit var tokenManager: TokenManager
    }

    override fun onCreate() {
        super.onCreate()
        tokenManager = TokenManager(this)
    }
}