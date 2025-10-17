package com.example.btvntuan3.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("UI Components List", style = androidx.compose.material3.MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        // Nút điều hướng đến màn hình Text
        ComponentButton(text = "Text") {
            navController.navigate("text_screen")
        }

        // Nút điều hướng đến màn hình Image
        ComponentButton(text = "Image") {
            navController.navigate("image_screen")
        }

        // Nút điều hướng đến màn hình TextField
        ComponentButton(text = "TextField") {
            navController.navigate("textfield_screen")
        }

        // Nút điều hướng đến màn hình Row Layout
        ComponentButton(text = "Row Layout") {
            navController.navigate("row_layout_screen")
        }
    }
}

// Hàm tái sử dụng để tạo nút cho gọn
@Composable
fun ComponentButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text)
    }
}