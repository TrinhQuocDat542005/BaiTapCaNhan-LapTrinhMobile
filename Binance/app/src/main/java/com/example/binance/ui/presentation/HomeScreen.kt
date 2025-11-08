package com.example.binance.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.binance.data.Coin

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val coinList by viewModel.coins.collectAsState()
    val error by viewModel.error.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101012)),
        contentAlignment = Alignment.Center
    ) {
        when {
            error != null -> {
                Text(text = error!!, color = Color.Red, modifier = Modifier.padding(16.dp))
            }

            coinList.isEmpty() -> {
                CircularProgressIndicator(color = Color.White)
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    // 🔸 Tiêu đề
                    item {
                        Text(
                            text = "Home",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    // 🔸 Banner Breaking News
                    item {
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            elevation = CardDefaults.cardElevation(4.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ) {
                            AsyncImage(
                                model = "https://cdn.cnn.com/cnnnext/dam/assets/200424060716-breaking-news-sign-stock-super-tease.jpg",
                                contentDescription = "Breaking News",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.height(120.dp)
                            )
                        }
                    }

                    // 🔸 Favorites section (2 coin đầu tiên)
                    item {
                        Text(
                            text = "Favorites",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                        )
                    }
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            coinList.take(2).forEach { coin ->
                                FavoriteCoinCard(coin)
                            }
                        }
                    }

                    // 🔸 Danh sách All Fluctuations
                    item {
                        Text(
                            text = "All Fluctuations",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
                        )
                    }

                    items(coinList) { coin ->
                        CoinRowItem(coin)
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteCoinCard(coin: Coin) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E22)
        ),
        modifier = Modifier
            .width(150.dp)
            .height(120.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = coin.image,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(text = coin.symbol.uppercase(), color = Color.White, fontWeight = FontWeight.Bold)
            }

            Text(
                text = "$${coin.currentPrice ?: 0.0}",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "+${"%.2f".format(coin.priceChangePercentage24h ?: 0.0)}%",
                color = if ((coin.priceChangePercentage24h ?: 0.0) >= 0) Color(0xFF00E676) else Color.Red,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun CoinRowItem(coin: Coin) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1F)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = coin.image,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(text = coin.symbol.uppercase(), color = Color.White, fontWeight = FontWeight.Bold)
                    Text(text = coin.name, color = Color.Gray)
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "$${"%.2f".format(coin.currentPrice ?: 0.0)}",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${"%.2f".format(coin.priceChangePercentage24h ?: 0.0)}%",
                    color = if ((coin.priceChangePercentage24h ?: 0.0) >= 0) Color(0xFF00E676) else Color.Red,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
