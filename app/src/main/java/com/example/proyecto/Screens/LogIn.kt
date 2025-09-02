package com.example.proyecto.Screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto.R


@Composable
@Preview(showBackground = true, device = "id:pixel_9a", showSystemUi = true)
fun preview(){
    LogIn(OnLogIn = {}, Preregister = {})
}
@Composable
fun LogIn(OnLogIn: () -> Unit, Preregister: () -> Unit){
    var username by rememberSaveable{mutableStateOf("")}
    var cellphone by rememberSaveable { mutableStateOf("") }
    val logo = R.drawable.logo
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(0.2f)) // pushes image down
        Image(
            painter = painterResource(id = logo),
            contentDescription = "Caritas logo", modifier = Modifier
                .fillMaxWidth(0.6f)
                .aspectRatio(1f),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            value = cellphone,
            onValueChange = { cellphone = it },
            label = { Text("Numero de Telefono") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { validator(OnLogIn, username, cellphone) },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Iniciar Sesion") }
        Spacer(modifier = Modifier.height(5.dp))
        Button(
            onClick = { Preregister() },
            modifier = Modifier.fillMaxWidth()
        ) { Text("PreRegister") }

        Spacer(modifier = Modifier.weight(0.5f)) // pushes content upwards to balance
    }

}

fun validator(OnLogIn: () -> Unit, Username: String, phoneNumber: String) {
    if (Username.isNotBlank() && phoneNumber.isNotBlank()) {
        // Later replace with API call == 200
        OnLogIn()
    } else {
        // TODO: Show error
        println("Invalid credentials")
    }
}


