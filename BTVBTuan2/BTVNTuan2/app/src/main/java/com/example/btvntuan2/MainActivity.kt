package com.example.btvntuan2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.btvntuan2.ui.theme.BTVNTuan2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Thay YourProjectTheme bằng tên theme của bạn, ví dụ: BaiTap2Theme
            BTVNTuan2Theme {
                AgeClassifierScreen()
            }
        }
    }
}

@Composable
fun AgeClassifierScreen() {
    // --- BƯỚC 2: TẠO CÁC BIẾN TRẠNG THÁI ---
    var name by remember { mutableStateOf("") }
    var ageInput by remember { mutableStateOf("") } // Dùng để lưu tuổi dạng chuỗi
    var result by remember { mutableStateOf("") }   // Dùng để lưu kết quả thông báo

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "THỰC HÀNH 01", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(32.dp))

        // Ô nhập họ và tên
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Họ và tên") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Ô nhập tuổi
        OutlinedTextField(
            value = ageInput,
            onValueChange = { ageInput = it },
            label = { Text("Tuổi") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Chỉ cho phép nhập số
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Nút kiểm tra
        Button(
            onClick = {
                val age = ageInput.toIntOrNull()

                if (name.isBlank() || ageInput.isBlank()) {
                    result = "Vui lòng nhập đầy đủ họ tên và tuổi."
                } else if (age == null) {
                    result = "Tuổi phải là một con số hợp lệ."
                } else {
                    // Nếu nhập đúng số, tiến hành phân loại
                    val category = when {
                        age > 65 -> "Người già"
                        age in 6..65 -> "Người lớn"
                        age in 2..5 -> "Trẻ em"
                        age >= 0 && age < 2 -> "Em bé"
                        else -> "Tuổi không hợp lệ" // Xử lý trường hợp tuổi là số âm
                    }

                    // Tạo ra chuỗi kết quả cuối cùng
                    result = "Thông tin: $name - $age tuổi\nKết quả: Bạn là $category"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kiểm tra")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Hiển thị kết quả
        if (result.isNotEmpty()) {
            Text(
                text = result,
                fontSize = 18.sp
            )
        }
    }
}