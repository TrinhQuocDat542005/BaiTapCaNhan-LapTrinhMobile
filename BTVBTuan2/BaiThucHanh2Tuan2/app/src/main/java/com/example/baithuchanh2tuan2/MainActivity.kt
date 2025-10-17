package com.example.baithuchanh2tuan2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.baithuchanh2tuan2.ui.theme.BaiThucHanh2Tuan2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Thay YourProjectTheme bằng tên theme của dự án bạn
            BaiThucHanh2Tuan2Theme {
                EmailValidatorScreen()
            }
        }
    }
}

@Composable
fun EmailValidatorScreen() {
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var messageColor by remember { mutableStateOf(Color.Red) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Thực hành 02", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))

        // Ô nhập liệu Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        // Hiển thị thông báo
        if (message.isNotEmpty()) {
            Text(
                text = message,
                color = messageColor,
                modifier = Modifier.padding(top = 8.dp).align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nút bấm "Kiểm tra"
        Button(
            onClick = {
                if (email.isNullOrEmpty()) {
                    message = "Email không hợp lệ"
                    messageColor = Color.Red
                } else if (!email.contains("@")) {
                    message = "Email không đúng định dạng"
                    messageColor = Color.Red
                } else {
                    message = "Bạn đã nhập email hợp lệ"
                    messageColor = Color(0xFF006400) // Màu xanh lá cây đậm
                }
                // Logic kiểm tra sẽ được thêm ở Bước 2
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kiểm tra")
        }
    }
}