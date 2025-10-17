package com.example.btvntuan3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.btvntuan3.screens.*
import com.example.btvntuan3.ui.theme.BTVNTuan3Theme // Thay bằng tên theme của bạn

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BTVNTuan3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    // Tạo một NavController để quản lý việc chuyển màn hình
    val navController = rememberNavController()

    // NavHost là nơi chứa tất cả các màn hình (destination)
    NavHost(navController = navController, startDestination = "main_screen") {
        // Định nghĩa màn hình chính
        composable("main_screen") {
            MainScreen(navController = navController)
        }
        // Định nghĩa các màn hình con
        composable("text_screen") {
            TextScreen()
        }
        composable("image_screen") {
            ImageScreen()
        }
        composable("textfield_screen") {
            TextFieldScreen()
        }
        composable("row_layout_screen") {
            RowLayoutScreen()
        }
    }
}