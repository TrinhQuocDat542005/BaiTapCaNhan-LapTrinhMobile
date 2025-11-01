package com.example.uthsmarttasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uthsmarttasks.model.Product
import com.example.uthsmarttasks.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.util.Log

class ProductViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList()) // ✅ list
    val products = _products.asStateFlow()

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getProducts() // ✅ trả list
                Log.d("ProductVM", "API Response size: ${response.size}")
                _products.value = response
            } catch (e: Exception) {
                Log.e("ProductVM", "Error fetching products", e)
            }
        }
    }
}
