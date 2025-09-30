package com.example.proyecto.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun Preview2(){
    OtpScreen({},{})
}

@Composable
fun OtpScreen(
    onOtpSubmit: (String) -> Unit,
    onResend: () -> Unit
) {
    var otp by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Enter the Code sent to your phone", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(24.dp))

        // OTP TextField
        TextField(
            value = otp,
            onValueChange = { value ->
                if (value.length <= 6 && value.all { it.isDigit() }) {
                    otp = value
                }
            },
            placeholder = { Text("000000") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(.9f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Submit Button
        Button(
            onClick = { if (otp.length == 4) onOtpSubmit(otp) },
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Text("Submit")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Resend OTP
        TextButton(onClick = onResend) {
            Text("Resend OTP")
        }
    }
}
