package com.example.binance.network

// Anh phải import các thư viện này vào
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory // Dùng Moshi, không phải Gson

object RetrofitInstance {

    // 1. Tạo một đối tượng Moshi để nó hiểu code Kotlin
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // 2. Xây dựng Retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/") // Đây là link API gốc
            // 3. Dùng MoshiConverterFactory và truyền moshi object vào
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    // 4. Tạo Api service
    val api: CoinGeckoApi by lazy {
        retrofit.create(CoinGeckoApi::class.java)
    }
}