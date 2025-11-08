package com.example.binance.network

// Anh phải import các thư viện này vào
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory // Dùng Moshi, không phải Gson

object RetrofitInstance {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val api: CoinGeckoApi by lazy {
        retrofit.create(CoinGeckoApi::class.java)
    }
}