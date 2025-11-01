package com.example.uthsmarttasks.network

import com.example.uthsmarttasks.model.Product
import retrofit2.http.GET

interface ApiService {

    // Nếu API trả về danh sách sản phẩm
    @GET("product")
    suspend fun getProducts(): List<Product>
}
