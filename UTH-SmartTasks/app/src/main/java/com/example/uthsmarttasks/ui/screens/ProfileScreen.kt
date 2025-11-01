package com.example.uthsmarttasks.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@Composable
fun ProfileScreen(
    user: FirebaseUser?,
    navController: NavController
) {
    val auth = FirebaseAuth.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
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

        // ✅ Nút xem chi tiết sản phẩm
        Button(onClick = {
            navController.navigate("product")
        }) {
            Text("Xem chi tiết sản phẩm")
        }

        Spacer(modifier = Modifier.height(16.dp))

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
