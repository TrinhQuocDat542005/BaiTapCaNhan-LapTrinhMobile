package com.example.uthsmarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uthsmarttasks.ui.screens.LoginScreen
import com.example.uthsmarttasks.ui.screens.ProfileScreen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.example.uthsmarttasks.ui.screens.ProductScreen

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        // Cấu hình Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        setContent {
            val navController = rememberNavController()

            // Nếu user đã đăng nhập sẵn -> vào profile luôn
            val startDestination = if (auth.currentUser != null) "profile" else "login"

            NavHost(navController = navController, startDestination = startDestination) {

                composable("login") {
                    LoginScreen(
                        navController = navController,
                        auth = auth,
                        googleSignInClient = googleSignInClient
                    )
                }

                composable("profile") {
                    val user = auth.currentUser
                    if (user == null) {
                        // Nếu chưa login (ví dụ: vừa logout), quay lại login
                        LaunchedEffect(Unit) {
                            navController.navigate("login") {
                                popUpTo("profile") { inclusive = true }
                            }
                        }
                    } else {
                        ProfileScreen(
                            user = user,
                            navController = navController
                        )
                    }
                }
                composable("product") {
                    ProductScreen(navController = navController)
                }

            }
        }
    }

}