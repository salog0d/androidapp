package com.example.proyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyecto.screens.LogIn
import com.example.proyecto.screens.PreRegistroScreen
import com.example.proyecto.ui.theme.ProyectoTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoTheme {
                LogIn(
                    OnLogIn = { /* navegar a home */ },
                    Preregister = { "PreRegistroScreen()" }
                )
            }
        }
    }
}

// ---- Preview ----
@Preview(showBackground = true)
@Composable
fun LogInPreview() {
    ProyectoTheme {
        LogIn(
            OnLogIn = { },
            Preregister = { "PreRegistroScreen()" }
        )
    }
}
