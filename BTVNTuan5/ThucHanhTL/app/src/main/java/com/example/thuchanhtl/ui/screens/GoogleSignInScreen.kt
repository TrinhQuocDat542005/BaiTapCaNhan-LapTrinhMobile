package com.example.thuchanhtl.ui.screens

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun GoogleLoginScreen() {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    var message by remember { mutableStateOf("") }

    // Cấu hình Google Sign In
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("YOUR_WEB_CLIENT_ID_HERE") // ⚠️ Sẽ hướng dẫn lấy bên dưới
        .requestEmail()
        .build()
    val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(context, gso)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.result
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                auth.signInWithCredential(credential)
                    .addOnCompleteListener { taskAuth ->
                        if (taskAuth.isSuccessful) {
                            val email = auth.currentUser?.email
                            message = "Success!\nHi $email\nWelcome to UTHSmartTasks"
                        } else {
                            message = "Login failed: ${taskAuth.exception?.message}"
                        }
                    }
            } catch (e: Exception) {
                message = "Google Sign-In Failed: ${e.message}"
            }
        } else {
            message = "Canceled by user"
        }
    }

    // UI hiển thị
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            val signInIntent = googleSignInClient.signInIntent
            launcher.launch(signInIntent)
        }) {
            Text("Login by Gmail")
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (message.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .background(
                        if (message.startsWith("Success")) Color(0xFFB3E5FC)
                        else Color(0xFFFFCDD2)
                    )
                    .padding(16.dp)
                    .width(280.dp)
            ) {
                Text(text = message, fontSize = 16.sp, color = Color.Black)
            }
        }
    }
}
