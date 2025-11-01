package com.example.uthsmarttasks.ui.screens

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun LoginScreen(navController: NavController, auth: FirebaseAuth) {
    val context = LocalContext.current
    var loading by remember { mutableStateOf(false) }

    // Launcher nhận kết quả đăng nhập Google
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.result
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                loading = true
                auth.signInWithCredential(credential)
                    .addOnCompleteListener { signInTask ->
                        loading = false
                        if (signInTask.isSuccessful) {
                            Log.d("LoginScreen", "Đăng nhập Firebase thành công")
                            navController.navigate("profile") {
                                popUpTo("login") { inclusive = true }
                            }
                        } else {
                            Log.e("LoginScreen", "Đăng nhập Firebase thất bại: ${signInTask.exception}")
                        }
                    }
            } catch (e: Exception) {
                Log.e("LoginScreen", "Đăng nhập Google thất bại", e)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("UTH SmartTasks", fontSize = 28.sp)
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken("YOUR_WEB_CLIENT_ID")
                    .requestEmail()
                    .build()
                val client = GoogleSignIn.getClient(context, gso)
                launcher.launch(client.signInIntent)
            },
            enabled = !loading
        ) {
            Text(if (loading) "Đang đăng nhập..." else "Đăng nhập bằng Google")
        }
    }
}
