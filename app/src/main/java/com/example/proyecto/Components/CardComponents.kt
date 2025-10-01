package com.example.proyecto.Components

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyecto.models.HServicesScheduleData
import com.example.proyecto.models.Hostel
import com.example.proyecto.models.HostelServices
import com.example.proyecto.models.Location

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val scheduleData = HServicesScheduleData(
    created_at = "2025-09-30T16:00:00Z",
    created_by_name = "Admin User",
    day_name = "Monday",
    day_of_week = 1,
    duration_hours = 12,
    end_time = "20:00",
    id = "schedule_001",
    is_available = true,
    start_time = "08:00",
    updated_at = "2025-09-30T16:30:00Z"
)

val hostelService = HostelServices(
    created_at = "2025-09-30T16:30:00Z",
    created_by_name = "Admin User",
    hostel = "hostel_001",
    hostel_location = "loc_001",
    hostel_name = "Montaña Hostel",
    id = "service_001",
    is_active = true,
    schedule = "08:00 - 20:00",
    schedule_data = scheduleData,
    service = "cleaning",
    service_description = "Daily room cleaning and bed making",
    service_max_time = 60,  // in minutes
    service_name = "Room Cleaning",
    service_needs_approval = false,
    service_price = "200 MXN",
    total_reservations = 15,
    updated_at = "2025-09-30T16:45:00Z"
)


val myHostel = Hostel(
    created_at = "2025-09-30T16:00:00Z",
    id = "hostel_001",
    is_active = true,
    location = Location(
        address = "Av. Universidad 123",
        city = "Monterrey",
        country = "Mexico",
        id = "loc_001",
        landmarks = "Near Macroplaza",
        latitude = 25,          // integers as your class expects
        longitude = -100,
        state = "Nuevo León",
        zip_code = "64000"
    ),
    men_capacity = 50,
    name = "Montaña Hostel",
    phone = "+52 81 1234 5678",
    women_capacity = 40,
    current_men_capacity = 10,
    current_women_capacity = 10
)
@Composable
fun HostelCard(hostel: Hostel) {
    val totalCapacity = hostel.men_capacity + hostel.women_capacity
    val freeCapacity = (totalCapacity - hostel.current_men_capacity - hostel.current_women_capacity).coerceAtLeast(0)
    val freePercentage = if (totalCapacity > 0) freeCapacity.toFloat() / totalCapacity else 0f

    val indicatorColor = when {
        freeCapacity == 0 -> Color.Red
        freePercentage > 0.2 -> Color.Green
        else -> Color.Yellow
    }

    Card(
        modifier = Modifier
            .padding(6.dp)
            .wrapContentSize(),  // ensures card is just as big as content
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .wrapContentSize()
        ) {
            // Title + Indicator
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = hostel.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.width(6.dp))
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .clip(CircleShape)
                        .background(indicatorColor)
                )
            }

            Text(
                text = "${hostel.location.city}, ${hostel.location.state}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Tel: ${hostel.phone}",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row {
                Text(
                    text = "Hombres: ${hostel.men_capacity - hostel.current_men_capacity}",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Mujeres: ${hostel.women_capacity - hostel.current_women_capacity}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Text(
                text = "Total: $freeCapacity (Total: $totalCapacity)",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHostelCard() {
    HostelCard(hostel = myHostel)
}



@Composable
fun ServiceCard(service: HostelServices) {
    // Color según el estatus
    val statusColor = if (service.is_active) Color.Green else Color.Red

    Card(
        modifier = Modifier
            .padding(4.dp)
            .wrapContentSize(), // tarjeta ajustada al contenido
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(6.dp)
                .wrapContentSize()
        ) {
            // Nombre del servicio
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = service.service_name,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.width(6.dp))
                // Indicador de estatus como círculo
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(statusColor)
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            // Descripción
            Text(
                text = service.service_description,
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(2.dp))

            // Albergue
            Text(
                text = "Albergue: ${service.hostel_name}",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(2.dp))

            // Requiere aprobación
            Text(
                text = "Requiere aprobación: ${if (service.service_needs_approval) "Sí" else "No"}",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(2.dp))

            // Precio y duración en una línea
            Row {
                Text(
                    text = "Precio: ${service.service_price}",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Duración: ${service.service_max_time} min",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            // Horario
            Text(
                text = "Horario: ${service.schedule}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewServiceCard() {
    ServiceCard(service = hostelService)
}



