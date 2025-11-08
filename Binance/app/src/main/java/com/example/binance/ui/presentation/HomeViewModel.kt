package com.example.binance.ui.presentation

// Import các thư viện quan trọng
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binance.data.Coin
import com.example.binance.network.RetrofitInstance
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception // Dùng cho try-catch
import android.util.Log


class HomeViewModel : ViewModel() {

    // Đây là nơi UI (HomeScreen) sẽ "nhìn" vào để lấy danh sách coin
    // Nó là "private" (riêng tư)
    private val _coins = MutableStateFlow<List<Coin>>(emptyList())

    // Đây là bản "public" (công khai) để cho HomeScreen đọc
    val coins: StateFlow<List<Coin>> = _coins

    // Biến cờ để báo lỗi
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // Hàm `init` này sẽ tự động chạy ngay khi HomeViewModel được tạo
    init {
        startFetching()
    }

    private fun startFetching() {
        viewModelScope.launch {
            while (true) {
                try {
                    val coinList = RetrofitInstance.api.getMarkets(currency = "usd")
                    Log.d("CoinFetch", "Fetched: ${coinList[0].name} = ${coinList[0].currentPrice}")

                    _coins.value = coinList
                    _error.value = null
                } catch (e: Exception) {
                    _error.value = "Không thể tải dữ liệu: ${e.message}"
                }
                delay(30000)
            }   
        }
    }
}