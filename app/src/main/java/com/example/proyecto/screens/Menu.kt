package com.example.proyecto.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto.components.HostelCard
import com.example.proyecto.components.ServiceCard
import com.example.proyecto.components.BottomNavMenu
import com.example.proyecto.components.hostelService
import com.example.proyecto.components.myHostel
import com.example.proyecto.ui.theme.Gotham

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewReservationScreenLayout() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Menú Principal",
                        fontFamily = Gotham,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp // más pequeño
                    )
                },
            )
        },
    ) { inner ->
        // 🔹 Esto asegura que Compose no herede escalado de texto del sistema
        CompositionLocalProvider(
            LocalDensity provides Density(
                LocalDensity.current.density,
                fontScale = 1f // Fuerza el tamaño de fuente real
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(inner)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 🔹 Sección de Albergues
                Text(
                    text = "Albergues disponibles",
                    fontFamily = Gotham,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp, // 🔹 tamaño fijo
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(Modifier.height(8.dp))

                val hostelList = listOf(
                    myHostel,
                    myHostel.copy(id = "hostel_002", name = "Valle Hostel")
                )

                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(hostelList) { hostel ->
                        HostelCard(hostel)
                    }
                }

                Spacer(Modifier.height(20.dp))

                // 🔹 Sección de Servicios
                Text(
                    text = "Servicios disponibles",
                    fontFamily = Gotham,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(Modifier.height(8.dp))

                val serviceList = listOf(
                    hostelService,
                    hostelService.copy(id = "service_002", service_name = "Laundry")
                )

                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(serviceList) { service ->
                        ServiceCard(service)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewReservationScreenLayoutPreview() {
    PreviewReservationScreenLayout()
}

