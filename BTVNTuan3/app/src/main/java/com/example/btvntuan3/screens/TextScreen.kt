package com.example.btvntuan3.screens

import androidx.compose.foundation.layout.*
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
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 32.sp)) {
                    append("The quick ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color(0xFF8B4513))) {
                        append("Brown")
                    }
                    append(" fox jumps ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 48.sp)) {
                        append("over")
                    }
                    append(" the lazy dog.")
                }
            }
        )
    }
}