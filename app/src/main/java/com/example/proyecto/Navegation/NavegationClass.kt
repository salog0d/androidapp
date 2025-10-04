package com.example.proyecto.Navegation

sealed class Screen(val route: String) {
    data object Home : Screen("Home")
    data object Reservation : Screen("Reservation")
    data object Historial : Screen("Historial")
    data object Profile : Screen("Perfil")
    data object Login : Screen("Login")
    data object OTP : Screen("OTP")
    data object Register : Screen("Register")

    // HostelReservation screen with hostelId placeholder
    data object HostelReservation : Screen("HostelReservation?hostelId={hostelId}") {
        fun createRoute(hostelId: String? = null): String {
            return if (hostelId != null) {
                "HostelReservation?hostelId=$hostelId"
            } else {
                "HostelReservation" // no id
            }
        }
    }

    data object ServiceReservation : Screen("ServiceReservation?serviceId={serviceId}") {
        fun createRoute(serviceId: String? = null): String {
            return if (serviceId != null) {
                "ServiceReservation?serviceId=$serviceId"
            } else {
                "ServiceReservation" // no id
            }
        }
    }

    // Hostel info screen with hostelId placeholder
    data object HostelInfo : Screen("HostelInfo/{hostelId}") {
        fun createRoute(hostelId: String) = "HostelInfo/$hostelId"
    }

    // Service info screen with serviceId placeholder
    data object ServiceInfo : Screen("ServiceInfo/{serviceId}") {
        fun createRoute(serviceId: String) = "ServiceInfo/$serviceId"
    }
}

