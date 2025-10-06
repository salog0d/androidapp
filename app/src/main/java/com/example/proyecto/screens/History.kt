package com.example.proyecto.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.proyecto.R
import com.example.proyecto.ViewModel.GeneralViewModel
import com.example.proyecto.data.ResultState
import com.example.proyecto.models.MyHostelReservations
import com.example.proyecto.models.MyServiceReservations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialScreen(vm: GeneralViewModel, onBack: () -> Unit = {}) {
    var tab by remember { mutableStateOf(0) }

    val hostelState = vm.myHostelReservationsState.collectAsState()
    val serviceState = vm.myServiceReservationsState.collectAsState()

    LaunchedEffect(Unit) {
        vm.fetchMyHostelReservations()
        vm.fetchMyServiceReservations()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Historial") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.purple_200),
                    titleContentColor = colorResource(id = R.color.white)
                )
            )
        }
    ) { inner ->
        Column(modifier = Modifier.padding(inner).fillMaxSize()) {
            TabRow(selectedTabIndex = tab) {
                Tab(selected = tab == 0, onClick = { tab = 0 }, text = { Text("Hospedaje") })
                Tab(selected = tab == 1, onClick = { tab = 1 }, text = { Text("Servicios") })
            }

            when (tab) {
                0 -> when (val st = hostelState.value) {
                    is ResultState.Idle, is ResultState.Loading -> LoadingBox()
                    is ResultState.Error -> ErrorBox(st.message ?: "Error")
                    is ResultState.Success -> HostelHistoryList(st.data.myHostelReservations)
                }
                1 -> when (val st = serviceState.value) {
                    is ResultState.Idle, is ResultState.Loading -> LoadingBox()
                    is ResultState.Error -> ErrorBox(st.message ?: "Error")
                    is ResultState.Success -> ServiceHistoryList(st.data.myServiceReservations)
                }
            }
        }
    }
}

@Composable private fun LoadingBox() = Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }

@Composable private fun ErrorBox(msg: String) = Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text(msg, color = MaterialTheme.colorScheme.error) }

@Composable private fun EmptyState(msg: String) = Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text(msg) }

@Composable
private fun HostelHistoryList(items: List<MyHostelReservations>) {
    if (items.isEmpty()) return EmptyState("No tienes reservas de hospedaje.")
    LazyColumn(Modifier.padding(12.dp)) {
        items(items) {
            ElevatedCard(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(12.dp)) {
                    Text(it.hostel_name, fontWeight = FontWeight.Bold)
                    Text("Llegada: ${it.arrival_date}")
                    Text("Personas: ${it.total_people}")
                    Text("Estado: ${it.status_display}")
                }
            }
        }
    }
}

@Composable
private fun ServiceHistoryList(items: List<MyServiceReservations>) {
    if (items.isEmpty()) return EmptyState("No tienes reservas de servicios.")
    LazyColumn(Modifier.padding(12.dp)) {
        items(items) {
            ElevatedCard(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(12.dp)) {
                    Text(it.service_name, fontWeight = FontWeight.Bold)
                    Text("Inicio: ${it.datetime_reserved}")
                    Text("Duración: ${it.duration_minutes} min")
                    Text("Estado: ${it.status_display}")
                }
            }
        }
    }
}
