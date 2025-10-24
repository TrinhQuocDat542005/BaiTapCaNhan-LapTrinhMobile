package com.example.btvn01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.btvn01.ui.screens.LibraryScreen
import com.example.btvn01.ui.theme.BTVN01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BTVN01Theme {
                LibraryScreen()
            }
        }
    }
}
