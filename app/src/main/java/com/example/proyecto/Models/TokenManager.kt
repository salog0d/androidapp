package com.example.proyecto.Models

import android.app.Application
import com.example.proyecto.Utilities.TokenManager

class MyApp : Application() {
    companion object {
        lateinit var tokenManager: TokenManager
    }

    override fun onCreate() {
        super.onCreate()
        tokenManager = TokenManager(this)
    }
}