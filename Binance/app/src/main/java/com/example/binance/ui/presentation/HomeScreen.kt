package com.example.binance.ui.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
// 🚀 Thêm thư viện này cho vòng xoay "loading"
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.binance.data.Coin

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    val coinList by viewModel.coins.collectAsState()
    val error by viewModel.error.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center // Căn giữa
    ) {
        if (error != null) {
            // Hiển thị lỗi (giống như anh vừa thấy)
            Text(text = error!!, color = Color.Red, modifier = Modifier.padding(16.dp))
        } else if (coinList.isEmpty()) {
            // 🚀 Hiển thị vòng xoay "loading" khi chưa có data
            CircularProgressIndicator()
        } else {
            // Hiển thị danh sách
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(coinList) { coin ->
                    CoinItem(coin = coin)
                }
            }
        }
    }
}

@Composable
fun CoinItem(coin: Coin) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp), // Tăng khoảng cách
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = coin.image,
                contentDescription = "${coin.name} logo",
                modifier = Modifier.size(32.dp) // Cho ảnh to hơn
            )
            Column {
                Text(text = coin.name, fontWeight = FontWeight.Bold)
                Text(text = coin.symbol.uppercase(), color = Color.Gray)
            }
        }
        Column(horizontalAlignment = Alignment.End) {
            // 🚀 BƯỚC 1: XỬ LÝ NULL CHO GIÁ
            // Nếu giá là null, mình hiện "N/A"
            val priceText = if (coin.currentPrice != null) {
                "$${coin.currentPrice}"
            } else {
                "N/A"
            }
            Text(text = priceText, fontWeight = FontWeight.Bold)

            // 🚀 BƯỚC 2: XỬ LÝ NULL CHO % THAY ĐỔI
            // Nếu % là null, mình coi như là 0.0
            val percentage = coin.priceChangePercentage24h ?: 0.0

            Text(
                text = "${"%.2f".format(percentage)}%",
                // Dùng biến `percentage` đã xử lý null
                color = if (percentage >= 0) Color(0xFF00C853) else Color.Red
            )
        }
    }
}