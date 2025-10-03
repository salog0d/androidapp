package com.example.proyecto.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.proyecto.R
import com.example.proyecto.ui.theme.Gotham


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationScreen(
    onBack: () -> Unit = {},
    onClickHostel: () -> Unit = {},
    onClickService: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Nueva Reserva",
                    fontFamily = Gotham,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ) },
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
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Selecciona el tipo de Reserva",
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = Gotham,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
            Spacer(Modifier.height(12.dp))

            val cPrimary = colorResource(R.color.pantone_320)
            val cPrimaryDk = colorResource(R.color.pantone_302)
            val cWhite = colorResource(R.color.white)

            Button(
                onClick = { onClickHostel },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = cPrimary,
                    contentColor = cWhite
                )
            ) {
                Text("Reserva de Albergue", style = MaterialTheme.typography.titleMedium)
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { onClickService },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = cPrimaryDk,
                    contentColor = cWhite
                )
            ) {
                Text("Reserva de Servicio", style = MaterialTheme.typography.titleMedium)
            }

            Spacer(Modifier.height(24.dp))
        }

    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
private fun ReservationScreenPreview() {
    ReservationScreen()
}
