// Trong file: ui/screens/ProfileScreen.kt
package com.example.uthsmarttasks.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    user: FirebaseUser?,
    navController: NavController
) {
    val auth = FirebaseAuth.getInstance()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Thông tin cá nhân") },
                // Nút quay lại
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) { // 👈 Bấm để quay lại
                        Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding) // 👈 Dùng padding của Scaffold
                .padding(24.dp), // Thêm padding của riêng anh
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Avatar (nếu có)
            user?.photoUrl?.let {
                AsyncImage(model = it, contentDescription = "Avatar", modifier = Modifier.size(120.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text("Tên: ${user?.displayName ?: "N/A"}")
            Text("Email: ${user?.email ?: "N/A"}")
            Spacer(modifier = Modifier.height(24.dp))

            // Nút đăng xuất
            Button(onClick = {
                auth.signOut()
                navController.navigate("login") {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            }) {
                Text("Đăng xuất")
            }
        }
    }
}