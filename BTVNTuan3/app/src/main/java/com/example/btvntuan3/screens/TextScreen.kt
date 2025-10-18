package com.example.btvntuan3.screens // Giữ đúng package name của anh

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            // Bắt đầu xây dựng một chuỗi có nhiều định dạng
            buildAnnotatedString {
                // Áp dụng style chung cho cả câu: cỡ chữ 32.sp
                withStyle(style = SpanStyle(fontSize = 32.sp)) {
                    append("The quick ")

                    // Style cho chữ "Brown": In đậm và có màu nâu
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF8B4513) // Màu nâu đậm
                        )
                    ) {
                        append("Brown")
                    }

                    append(" fox jumps ")

                    // Style cho chữ "over": In đậm và cỡ chữ 48.sp (lớn hơn)
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 48.sp
                        )
                    ) {
                        append("over")
                    }

                    append(" the lazy dog.")
                }
            }
        )
    }
}