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
import com.example.proyecto.screens.ReservationFormScreen
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.proyecto.data.ResultState


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
                        nav.navigate("otp") {
                            popUpTo("preregister") { inclusive = true }
                        }
                    },
                    onBack = { nav.popBackStack() }
                )
            }
            composable("otp") {
                val otpState by generalViewModel.otpState.collectAsState()
                val context = LocalContext.current

                OtpScreen(
                    onOtpSubmit = { code ->
                        generalViewModel.updateOTP(code)
                        generalViewModel.verifyOTP(context = context)
                    },
                    onResend = {
                        generalViewModel.verifyLogin()
                    }
                )

                when (otpState) {
                    is ResultState.Loading -> {
                        CircularProgressIndicator()
                    }
                    is ResultState.Success<*> -> {
                        LaunchedEffect(Unit) {
                            nav.navigate("home") {
                                popUpTo("otp") { inclusive = true }
                            }
                        }
                    }
                    is ResultState.Error -> {
                        Text("OTP inválido o error de red")
                    }
                    else -> {}
                }
            }
            composable("home") {
                ReservationFormScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}
