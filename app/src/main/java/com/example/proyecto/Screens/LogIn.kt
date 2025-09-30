package com.example.proyecto.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyecto.Models.LoginState
import com.example.proyecto.R
import com.example.proyecto.ui.viewmodels.CountriesViewModel
import com.example.proyecto.ViewModel.GeneralViewModel


@Composable
@Preview(showBackground = true, device = "id:pixel_9a", showSystemUi = true)
fun preview(){
    LogIn(OnLogIn = {}, Preregister = {})
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogIn(
    OnLogIn: () -> Unit,
    Preregister: () -> Unit,
    vm: CountriesViewModel = viewModel(),
    VM: GeneralViewModel = viewModel()
) {
    val countries by vm.countries.collectAsState()
    val loginState by VM.loginState.collectAsState()

    var username by rememberSaveable { mutableStateOf("") }
    var localPhone by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedIndex by rememberSaveable { mutableStateOf(0) }
    val selected = countries.getOrNull(selectedIndex)

    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(0.2f))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier.fillMaxWidth(0.6f).aspectRatio(1f),
            contentScale = ContentScale.Fit
        )
        Spacer(Modifier.height(10.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(5.dp))

        Row(Modifier.fillMaxWidth()) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { if (countries.isNotEmpty()) expanded = !expanded },
                modifier = Modifier.width(140.dp)
            ) {
                OutlinedTextField(
                    readOnly = true,
                    value = selected?.let { "${it.name?.common.orEmpty()}  ${it.dialCode}" }
                        ?: "Cargando países…",
                    onValueChange = {},
                    label = { Text("País / Lada") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    countries.forEachIndexed { i, c ->
                        DropdownMenuItem(
                            text = { Text("${c.name?.common.orEmpty()}  ${c.dialCode}") },
                            onClick = { selectedIndex = i; expanded = false }
                        )
                    }
                }
            }
            Spacer(Modifier.width(8.dp))
            TextField(
                value = localPhone,
                onValueChange = { input -> localPhone = input.filter { it.isDigit() } },
                label = { Text("Teléfono") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(10.dp))
        Button(
            onClick = {
                val fullPhone = (selected?.dialCode ?: "") + localPhone
                VM.updatePhoneNumber(fullPhone)
                VM.verifyLogin()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = username.isNotBlank() && localPhone.isNotBlank()
        ) { Text("Iniciar Sesión") }

        Spacer(Modifier.height(5.dp))
        Button(onClick = {Preregister()}, modifier = Modifier.fillMaxWidth()) {
            Text("PreRegister")
        }
        Spacer(Modifier.weight(0.5f))
        when (loginState) {
            is LoginState.Loading -> CircularProgressIndicator()
            is LoginState.Success -> {
                OnLogIn()
            }
            is LoginState.Error -> {
                Text(text = (loginState as LoginState.Error).message, color = Color.Red)
            }
            else -> {}
        }
    }
}

