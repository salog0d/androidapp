package com.example.proyecto.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto.components.BottomNavMenu
import com.example.proyecto.components.ReservationPicker
import java.time.LocalDate
import java.time.LocalTime
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.proyecto.components.TimePickerField
import com.example.proyecto.ui.theme.Gotham
import com.example.proyecto.components.DatePickerField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationScreen(
    onBack: () -> Unit = {},
    onConfirm: (LocalDate, LocalTime) -> Unit = { _, _ -> }
) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var selectedTime by remember { mutableStateOf(LocalTime.of(11, 0)) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Nueva Reserva",
                        fontFamily = Gotham,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        },
        bottomBar = { BottomNavMenu(startRoute = "reservas") }
    ) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                "Selecciona Fecha y Hora",
                fontFamily = Gotham,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
            Spacer(Modifier.height(12.dp))

            // Picker combinado que ya tenías
            ReservationPicker(
                modifier = Modifier.fillMaxSize(),
                onConfirm = { date, time ->
                    println("Reserva confirmada para la fecha: $date y la hora: $time")
                }
            )

            Spacer(Modifier.height(16.dp))

            // DatePickerField integrado
            DatePickerField(
                label = "Arrival Date",
                onDateSelected = { selectedDate = it }
            )

            Spacer(Modifier.height(16.dp))

            // TimePickerField integrado
            TimePickerField(
                label = "Arrival Time",
                initialHour = selectedTime.hour,
                initialMinute = selectedTime.minute,
                onTimeSelected = { selectedTime = it }
            )
        }
    }
}


@Preview(
    showBackground = true,
    widthDp = 400,
    heightDp = 800,
    name = "Reserva - Default"
)
@Composable
fun ReservationScreenPreview() {
    ReservationScreen()
}

@Preview(
    showBackground = true,
    widthDp = 400,
    heightDp = 800,
    name = "Reserva - Custom Date/Time"
)
@Composable
fun ReservationScreenCustomPreview() {
    // Muestra el mismo componente pero podrías
    // pasar un onConfirm para probar la lógica
    ReservationScreen(
        onBack = { println("Back pressed") },
        onConfirm = { date, time ->
            println("Confirmada: $date $time")
        }
    )
}
