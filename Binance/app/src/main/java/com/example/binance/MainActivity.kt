package com.example.binance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
// 🚀 Thêm import cho HomeScreen của mình!
import com.example.binance.ui.presentation.HomeScreen
import com.example.binance.ui.theme.BinanceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Bỏ enableEdgeToEdge() đi cũng được ạ
        setContent {
            BinanceTheme {
                // Mình dùng Surface làm nền
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 🚀 GỌI MÀN HÌNH CHÍNH Ở ĐÂY!
                    HomeScreen()
                }
            }
        }
    }
}

// Mấy hàm Greeting() và GreetingPreview() ở dưới anh xóa đi cũng được,
// mình không dùng đến nó nữa ạ.