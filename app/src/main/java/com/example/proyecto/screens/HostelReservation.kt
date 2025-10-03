package com.example.proyecto.screens

import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.proyecto.ViewModel.GeneralViewModel
import com.example.proyecto.components.ReservationForm
import com.example.proyecto.data.ResultState
import com.example.proyecto.models.HostelList
import com.example.proyecto.models.NewHostelReservation

@Composable
fun ReservationScreen(
    viewModel: GeneralViewModel,
    userId: String,  // <- you’ll pass this from session / token manager
    preselectedHostelId: String? = null
) {
    val hostelListState by viewModel.hostelListState.collectAsState()
    val reservationState by viewModel.newHostelReservationState.collectAsState()

    // trigger fetching hostels when screen opens
    LaunchedEffect(Unit) {
        viewModel.fetchHostels()
    }

    when (hostelListState) {
        is ResultState.Loading -> Text("Loading hostels...")
        is ResultState.Error -> Text("Error: ${(hostelListState as ResultState.Error).message}")
        is ResultState.Success -> {
            val hostels = (hostelListState as ResultState.Success<HostelList>).data.hostels
            val hostelNames = hostels.map { it.name }
            val hostelIdMap = hostels.associateBy({ it.name }, { it.id })
            var selectedHostelName by remember { mutableStateOf("") }
            if (preselectedHostelId != null) {
                val preselectedHostel = hostels.find { it.id == preselectedHostelId }
                selectedHostelName = preselectedHostel?.name ?: ""
            }
            ReservationForm(
                hostels = hostelNames,
                preselectedHostel = selectedHostelName,
                hostelIdMap = hostelIdMap,
                userId = userId,
                onSubmitReservation = { request ->
                    viewModel.createHostelReservation(request)
                }
            )


        }
        else -> {}
    }

    when (reservationState) {
        is ResultState.Loading -> Text("Submitting reservation...")
        is ResultState.Success -> Text("Reservation created!")
        is ResultState.Error -> Text("Error: ${(reservationState as ResultState.Error).message}")
        else -> {}
    }
}
