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
import com.example.proyecto.Navegation.Screen
import com.example.proyecto.screens.LogIn
import com.example.proyecto.screens.PreRegistroScreen
import com.example.proyecto.screens.OtpScreen
import com.example.proyecto.ui.theme.ProyectoTheme
import com.example.proyecto.ViewModel.GeneralViewModel
import com.example.proyecto.screens.ReservationScreen

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

        NavHost(navController = nav, startDestination = Screen.Login.route) {

            // ------------------ LOGIN ------------------
            composable(Screen.Login.route) {
                LogIn(
                    OnLogIn = {
                        // 👉 After login, go to OTP instead of Home
                        nav.navigate(Screen.OTP.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    },
                    Preregister = {
                        nav.navigate(Screen.Register.route)
                    },
                    VM = generalViewModel
                )
            }

            // ------------------ REGISTER ------------------
            composable(Screen.Register.route) {
                PreRegistroScreen(
                    generalViewModel = generalViewModel,
                    onDone = {
                        nav.navigate(Screen.OTP.route) {
                            popUpTo(Screen.Register.route) { inclusive = true }
                        }
                    },
                    onBack = { nav.popBackStack() }
                )
            }

            // ------------------ OTP ------------------
            composable(Screen.OTP.route) {
                OtpScreen(
                    onOtpSubmit = { code ->
                        generalViewModel.updateOTP(code)
                        // After successful OTP validation -> go to Home
                        nav.navigate(Screen.Home.route) {
                            popUpTo(Screen.OTP.route) { inclusive = true }
                        }
                    },
                    onResend = { generalViewModel.verifyLogin() }
                )
            }

            // ------------------ HOME ------------------


            // ------------------ RESERVATION ------------------
            composable(Screen.Reservation.route) {
                ReservationScreen(
                    onClickHostel = {
                        nav.navigate(Screen.HostelReservation.createRoute()) // no hostelId
                    },
                    onClickService = {
                        nav.navigate(Screen.ServiceReservation.createRoute()) // no serviceId
                    },
                    onBack = { nav.popBackStack() }
                )



            }

            // ------------------ HOSTEL RESERVATION ------------------
            composable(Screen.HostelReservation.route) {

            }


            // ------------------ SERVICE RESERVATION ------------------
            composable(Screen.ServiceReservation.route) {

            }


            // ------------------ HOSTEL INFO ------------------
            composable(Screen.HostelInfo.route) {

            }


            // ------------------ SERVICE INFO ------------------
            composable(Screen.ServiceInfo.route) {

            }


            // ------------------ HISTORIAL ------------------
            composable(Screen.Historial.route) {

            }


            // ------------------ PROFILE ------------------
            composable(Screen.Profile.route) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}
