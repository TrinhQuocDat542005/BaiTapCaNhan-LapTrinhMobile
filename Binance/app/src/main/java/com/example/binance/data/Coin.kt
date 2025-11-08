package com.example.binance.data

import com.squareup.moshi.Json

data class Coin(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,

    @field:Json(name = "current_price")
    val currentPrice: Double?,

    @field:Json(name = "price_change_percentage_24h")
    val priceChangePercentage24h: Double?
)