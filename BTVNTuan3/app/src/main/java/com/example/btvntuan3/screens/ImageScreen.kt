package com.example.btvntuan3.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage // Thư viện để tải ảnh từ URL
import com.example.btvntuan3.R // Thay bằng package của bạn

@Composable
fun ImageScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Image from URL", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        // Cần thêm thư viện Coil để dùng AsyncImage
        AsyncImage(
            model ="https://www.google.com/imgres?q=tr%C6%B0%E1%BB%9Dng%20uth&imgurl=https%3A%2F%2Fxdcs.cdnchinhphu.vn%2F446259493575335936%2F2024%2F8%2F17%2Fgt1-1720610920454798297957-17239112310021499437011.jpg&imgrefurl=https%3A%2F%2Fxaydungchinhsach.chinhphu.vn%2Fdiem-chuan-truong-dai-hoc-giao-thong-van-tai-tphcm-2024-119240817231631002.htm&docid=0jWWOtCqX2vKzM&tbnid=k2pLDoiqztafGM&vet=12ahUKEwixucmCu6yQAxVPTWwGHXhuBkMQM3oECBkQAA..i&w=1000&h=667&hcb=2&ved=2ahUKEwixucmCu6yQAxVPTWwGHXhuBkMQM3oECBkQAA",
            contentDescription = "UTH Logo from URL",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text("Image from App Resource", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = R.drawable.uth_logo), // Đảm bảo bạn có file uth_logo.png trong drawable
            contentDescription = "UTH Logo from App"
        )
    }
}