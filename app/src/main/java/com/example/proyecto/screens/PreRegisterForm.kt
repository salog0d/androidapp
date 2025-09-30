package com.example.proyecto.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyecto.R
import com.example.proyecto.ViewModel.GeneralViewModel
import com.example.proyecto.data.ResultState
import com.example.proyecto.models.PreRegForm
import com.example.proyecto.ui.theme.viewmodels.CountriesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneWithCountryField(
    telefono: String,
    onTelefono: (String) -> Unit,
    dialCode: String,
    onDialCode: (String) -> Unit,
    countriesVM: CountriesViewModel,           // <- sin default viewModel()
    enabled: Boolean = true
) {
    val countries by countriesVM.countries.collectAsState()
    var expanded by rememberSaveable { mutableStateOf(false) }

    // Setear una lada por default cuando carguen los países
    LaunchedEffect(countries) {
        if (dialCode.isBlank() && countries.isNotEmpty()) {
            onDialCode(countries.first().dialCode)
        }
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { if (enabled && countries.isNotEmpty()) expanded = !expanded }
    ) {
        OutlinedTextField(
            value = telefono,
            onValueChange = { onTelefono(it.filter(Char::isDigit)) },
            label = { Text("Número de teléfono") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true,
            leadingIcon = {
                AssistChip(
                    onClick = { if (enabled && countries.isNotEmpty()) expanded = true },
                    label = { Text(if (dialCode.isNotBlank()) dialCode else "+") },
                    enabled = enabled
                )
            },
            enabled = enabled,
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            countries.forEach { c ->
                val code = c.dialCode
                DropdownMenuItem(
                    text = { Text("${c.name?.common.orEmpty()}  $code") },
                    onClick = { onDialCode(code); expanded = false }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreRegistroScreen(
    generalViewModel: GeneralViewModel = viewModel(),
    countriesVM: CountriesViewModel = viewModel()   // única instancia creada aquí
) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("Femenino") }
    val generos = listOf("Femenino", "Masculino", "Otro", "Prefiero no decir")
    var generoMenu by remember { mutableStateOf(false) }

    var telefono by remember { mutableStateOf("") }
    var dialCode by remember { mutableStateOf("") }
    var aceptarPoliticas by remember { mutableStateOf(false) }
    val ui by generalViewModel.preRegistroState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.caritas_bg),
            contentDescription = "Logo Cáritas",
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .aspectRatio(1f),
            contentScale = ContentScale.Fit
            // modifier = Modifier.size(200.dp).padding(bottom = 8.dp),
            // contentScale = ContentScale.Fit
        )
        Text("Pre-registro Cáritas Mty", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = nombre, onValueChange = { nombre = it },
            label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth(),
            enabled = ui !is ResultState.Loading
        )
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = apellido, onValueChange = { apellido = it },
            label = { Text("Apellido") }, modifier = Modifier.fillMaxWidth(),
            enabled = ui !is ResultState.Loading
        )
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = edad,
            onValueChange = { if (it.all(Char::isDigit) && it.length <= 3) edad = it },
            label = { Text("Edad") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            enabled = ui !is ResultState.Loading
        )
        Spacer(Modifier.height(12.dp))

        ExposedDropdownMenuBox(
            expanded = generoMenu && ui !is ResultState.Loading,
            onExpandedChange = { if (ui !is ResultState.Loading) generoMenu = !generoMenu }
        ) {
            OutlinedTextField(
                value = genero, onValueChange = {}, readOnly = true,
                label = { Text("Género") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = generoMenu) },
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                enabled = ui !is ResultState.Loading
            )
            ExposedDropdownMenu(expanded = generoMenu, onDismissRequest = { generoMenu = false }) {
                generos.forEach { g -> DropdownMenuItem(text = { Text(g) }, onClick = {
                    genero = g; generoMenu = false
                }) }
            }
        }
        Spacer(Modifier.height(12.dp))

        // Campo teléfono con lada a la izquierda
        PhoneWithCountryField(
            telefono = telefono,
            onTelefono = { telefono = it },
            dialCode = dialCode,
            onDialCode = { dialCode = it },
            countriesVM = countriesVM,          // pasa la MISMA instancia
            enabled = ui !is ResultState.Loading
        )
        Spacer(Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = aceptarPoliticas, onCheckedChange = { aceptarPoliticas = it },
                enabled = ui !is ResultState.Loading)
            Text("Acepto políticas de privacidad")
        }
        Spacer(Modifier.height(24.dp))

        val habilitado =
            nombre.isNotBlank() &&
                    apellido.isNotBlank() &&
                    telefono.isNotBlank() &&
                    edad.toIntOrNull() != null &&
                    genero.isNotBlank() &&
                    aceptarPoliticas

        Button(
            onClick = {
                val fullPhone = dialCode + telefono
                val req = PreRegForm(
                    first_name = nombre,
                    last_name = apellido,
                    phone_number = fullPhone,
                    age = edad.toInt(),
                    gender = genero,
                    privacy_policy_accepted = aceptarPoliticas
                )
                generalViewModel.submitPreRegistro(req)
            },
            enabled = habilitado && ui !is ResultState.Loading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (ui is ResultState.Loading) CircularProgressIndicator(Modifier.size(24.dp))
            else Text("Enviar Pre-registro")
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_9a", showSystemUi = true)
@Composable
private fun PreRegistroPreview() {
    PreRegistroScreen()
}
