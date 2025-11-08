// Trong file: ui/screens/LoginScreen.kt
package com.example.uthsmarttasks.ui.screens

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uthsmarttasks.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit // 👈 ✅ SỬA 1: Chỉ nhận 1 hàm onLoginSuccess
) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }

    // --- Logic đăng nhập (Tự khởi tạo bên trong) ---
    val auth = FirebaseAuth.getInstance()
    val gso = remember {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }
    val googleSignInClient = remember { GoogleSignIn.getClient(context, gso) }
    // --- Hết Logic ---

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                isLoading = true // Bắt đầu loading
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)

                auth.signInWithCredential(credential)
                    .addOnCompleteListener { signInResult ->
                        if (signInResult.isSuccessful) {
                            Toast.makeText(context, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()

                            // ✅ SỬA 2: Gọi onLoginSuccess()
                            onLoginSuccess()

                        } else {
                            Toast.makeText(context, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show()
                            isLoading = false
                        }
                    }
            } catch (e: ApiException) {
                Toast.makeText(context, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
                isLoading = false
            }
        } else {
            Log.w("LoginScreen", "Google Sign-In bị hủy.")
            isLoading = false // Ngừng loading nếu user hủy
        }
    }

    // 🧩 Giao diện giống hình (Giữ nguyên)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            // Logo UTH
            Image(
                painter = painterResource(id = R.drawable.uth_logo),
                contentDescription = "UTH Logo",
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 24.dp)
            )
            // Tên app
            Text(
                text = "SmartTasks",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "A simple and efficient to-do app",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(48.dp))
            // Welcome
            Text(
                text = "Welcome",
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Ready to explore? Log in to get started.",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(32.dp))
            // Nút Google Sign-In
            Button(
                onClick = {
                    isLoading = true // Bấm nút thì bắt đầu loading
                    val signInIntent = googleSignInClient.signInIntent
                    launcher.launch(signInIntent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color.Gray),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.gg_icon),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Sign in with Google",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }
    }
}