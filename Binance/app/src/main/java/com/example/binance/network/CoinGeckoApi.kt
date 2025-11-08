package com.example.binance.network

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.binance.data.Coin

interface CoinGeckoApi {

    @GET("api/v3/coins/markets")
    suspend fun getMarkets(
        @Query("vs_currency") currency: String = "usd",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") perPage: Int = 20,
        @Query("page") page: Int = 1,
        @Query("sparkline") sparkline: Boolean = false
    ): List<Coin>
}