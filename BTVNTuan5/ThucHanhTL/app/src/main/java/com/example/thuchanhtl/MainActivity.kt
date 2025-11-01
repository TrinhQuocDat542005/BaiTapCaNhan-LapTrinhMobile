package com.example.thuchanhtl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginDemoScreen()
        }
    }
}

@Composable
fun LoginDemoScreen() {
    // loginState mô phỏng các trạng thái:
    // "default" -> chưa đăng nhập
    // "success" -> đăng nhập thành công
    // "error" -> đăng nhập thất bại
    var loginState by remember { mutableStateOf("default") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Nút đăng nhập Gmail
            Button(
                onClick = {
                    // test mô phỏng
                    loginState = when (loginState) {
                        "default" -> "success"
                        "success" -> "error"
                        else -> "default"
                    }
                },
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = "Login by Gmail")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Hiển thị kết quả
            when (loginState) {
                "success" -> {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFB3E5FC))
                            .padding(16.dp)
                            .width(280.dp)
                    ) {
                        Text(
                            text = "Success!\nHi: sample@gmail.com\nWelcome to UTH SmartTasks",
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                    }
                }

                "error" -> {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFFFCDD2))
                            .padding(16.dp)
                            .width(280.dp)
                    ) {
                        Text(
                            text = "Google Sign-In Failed\nUser canceled or network error",
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                    }
                }

                else -> {}
            }
        }
    }
}
