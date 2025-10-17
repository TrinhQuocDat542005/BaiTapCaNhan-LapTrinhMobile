package com.example.btvntuan3.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RowLayoutScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        repeat(4) { // Lặp lại 4 lần để tạo 4 hàng
            SimpleRow()
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
private fun SimpleRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly, // Chia đều khoảng trống
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Tạo 3 ô vuông
        Box(modifier = Modifier.size(80.dp).background(Color(0xFF6495ED)))
        Box(modifier = Modifier.size(80.dp).background(Color(0xFF6495ED)))
        Box(modifier = Modifier.size(80.dp).background(Color.LightGray))
    }
}