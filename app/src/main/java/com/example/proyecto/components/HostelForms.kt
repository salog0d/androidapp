package com.example.proyecto.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// -------------------- MAIN FORM --------------------
@Composable
fun ReservationForm(
    hostels: List<String>,
    preselectedHostel: String? = null
) {
    var selectedHostel by remember { mutableStateOf(preselectedHostel ?: "") }
    var expanded by remember { mutableStateOf(false) }

    var reservationType by remember { mutableStateOf("individual") }
    var menCount by remember { mutableStateOf(0) }
    var womenCount by remember { mutableStateOf(0) }
    val totalCount = menCount + womenCount

    var arrivalDate by remember { mutableStateOf("") } // yyyy-MM-dd

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HostelSelector(
            hostels = hostels,
            selectedHostel = selectedHostel,
            expanded = expanded,
            onExpandChange = { expanded = it },
            onHostelSelected = { selectedHostel = it }
        )

        ReservationDetails(
            reservationType = reservationType,
            onTypeChange = { reservationType = it },
            menCount = menCount,
            womenCount = womenCount,
            onMenChange = { menCount = it },
            onWomenChange = { womenCount = it },
            arrivalDate = arrivalDate,
            onDateChange = { arrivalDate = it },
            totalCount = totalCount
        )

        SubmitReservationButton(
            selectedHostel = selectedHostel,
            reservationType = reservationType,
            arrivalDate = arrivalDate,
            menCount = menCount,
            womenCount = womenCount
        )
    }
}

// -------------------- COMPONENT 1 --------------------
@Composable
fun HostelSelector(
    hostels: List<String>,
    selectedHostel: String,
    expanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
    onHostelSelected: (String) -> Unit
) {
    Column {
        Text(text = "Select Hostel")
        Box {
            OutlinedTextField(
                value = selectedHostel,
                onValueChange = {},
                readOnly = true,
                label = { Text("Hostel") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { onExpandChange(true) }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { onExpandChange(false) }
            ) {
                hostels.forEach { hostel ->
                    DropdownMenuItem(
                        text = { Text(hostel) },
                        onClick = {
                            onHostelSelected(hostel)
                            onExpandChange(false)
                        }
                    )
                }
            }
        }
    }
}

// -------------------- COMPONENT 2 --------------------
@Composable
fun ReservationDetails(
    reservationType: String,
    onTypeChange: (String) -> Unit,
    menCount: Int,
    womenCount: Int,
    onMenChange: (Int) -> Unit,
    onWomenChange: (Int) -> Unit,
    arrivalDate: String,
    onDateChange: (String) -> Unit,
    totalCount: Int
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        // Reservation Type
        Text("Reservation Type")
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = reservationType == "individual",
                onClick = { onTypeChange("individual") }
            )
            Text("Individual", modifier = Modifier.padding(end = 16.dp))
            RadioButton(
                selected = reservationType == "group",
                onClick = { onTypeChange("group")}
            )
            Text("Group")
        }

        // Men counter
        CounterField(
            label = "Men",
            value = menCount,
            onIncrement = {
                if (reservationType == "group") {
                    onMenChange(menCount + 1)
                } else if (reservationType == "individual" && totalCount < 1) {
                    onMenChange(menCount + 1)
                }
            },
            onDecrement = {
                if (menCount > 0) onMenChange(menCount - 1)
            }
        )

        // Women counter
        CounterField(
            label = "Women",
            value = womenCount,
            onIncrement = {
                if (reservationType == "group") {
                    onWomenChange(womenCount + 1)
                } else if (reservationType == "individual" && totalCount < 1) {
                    onWomenChange(womenCount + 1)
                }
            },
            onDecrement = {
                if (womenCount > 0) onWomenChange(womenCount - 1)
            }
        )

        // Total
        Text("Total People: $totalCount")

        // ReservationPicker for date/time
        ReservationPicker(
            onConfirm = { date, time ->
                val formatted = "${date}T${time}"
                onDateChange(formatted)
            }
        )
    }
}


// -------------------- COMPONENT 3 --------------------
@Composable
fun SubmitReservationButton(
    selectedHostel: String,
    reservationType: String,
    arrivalDate: String,
    menCount: Int,
    womenCount: Int
) {
    Button(
        onClick = {
            println(
                """
                {
                  "user": "123e4567-e89b-12d3-a456-426614174000",
                  "hostel": "$selectedHostel",
                  "type": "$reservationType",
                  "arrival_date": "$arrivalDate",
                  "men_quantity": $menCount,
                  "women_quantity": $womenCount
                }
                """.trimIndent()
            )
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Submit Reservation")
    }
}

// -------------------- REUSABLE COUNTER --------------------
@Composable
fun CounterField(label: String, value: Int, onIncrement: () -> Unit, onDecrement: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(label)
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onDecrement) {
                Icon(Icons.Default.Remove, contentDescription = "Decrease $label")
            }
            Text(value.toString(), modifier = Modifier.padding(horizontal = 8.dp))
            IconButton(onClick = onIncrement) {
                Icon(Icons.Default.Add, contentDescription = "Increase $label")
            }
        }
    }
}

// -------------------- PREVIEWS --------------------
@Preview(showBackground = true)
@Composable
fun ReservationFormPreview_Empty() {
    ReservationForm(
        hostels = listOf("Hostel A", "Hostel B", "Hostel C")
    )
}

@Preview(showBackground = true)
@Composable
fun ReservationFormPreview_Prefilled() {
    ReservationForm(
        hostels = listOf("Hostel A", "Hostel B", "Hostel C"),
        preselectedHostel = "Hostel B"
    )
}
