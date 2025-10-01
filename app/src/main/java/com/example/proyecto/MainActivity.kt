// MainActivity.kt
package com.example.proyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto.screens.LogIn
import com.example.proyecto.screens.PreRegistroScreen
import com.example.proyecto.screens.OtpScreen
import com.example.proyecto.ui.theme.ProyectoTheme
import com.example.proyecto.ViewModel.GeneralViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { App() }
    }
}

@Composable
fun App() {
    ProyectoTheme {
        val nav = rememberNavController()
        val generalViewModel: GeneralViewModel = viewModel()

        NavHost(navController = nav, startDestination = "login") {
            composable("login") {
                LogIn(
                    OnLogIn = {
                        nav.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    Preregister = {
                        nav.navigate("preregister")
                    }
                )
            }
            composable("preregister") {
                PreRegistroScreen(
                    generalViewModel = generalViewModel,
                    onDone = {
                        // cuando el pre-registro sea exitoso
                        nav.navigate("otp") {
                            popUpTo("preregister") { inclusive = true }
                        }
                    },
                    onBack = {
                        nav.popBackStack()
                    }
                )
            }
            composable("otp") {
                OtpScreen(
                    onOtpSubmit = { code ->
                        generalViewModel.updateOTP(code)
                        // aquí puedes llamar generalViewModel.verifyOTP(context) si lo necesitas
                        nav.navigate("home") {
                            popUpTo("otp") { inclusive = true }
                        }
                    },
                    onResend = {
                        generalViewModel.verifyLogin()
                    }
                )
            }
            composable("home") {
                // TODO: tu pantalla Home
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}
