package com.example.btvn02.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmScreen(email: String, code: String, password: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Confirm Information")
        Spacer(Modifier.height(16.dp))
        Text("Email: $email")
        Text("Code: $code")
        Text("Password: $password")
        Spacer(Modifier.height(16.dp))
        Button(onClick = { /* navigate back or finish */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Submit")
        }
    }
}
