package com.example.btvn02.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.btvn02.ui.screens.*

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "forget_password") {
        composable("forget_password") {
            ForgetPasswordScreen(onNext = { email ->
                navController.navigate("verify_code/$email")
            })
        }

        composable(
            "verify_code/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            VerifyCodeScreen(email = email, onNext = { code ->
                navController.navigate("reset_password/$email/$code")
            })
        }

        composable(
            "reset_password/{email}/{code}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("code") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val code = backStackEntry.arguments?.getString("code") ?: ""
            ResetPasswordScreen(email = email, code = code, onNext = { password ->
                navController.navigate("confirm/$email/$code/$password")
            })
        }

        composable(
            "confirm/{email}/{code}/{password}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("code") { type = NavType.StringType },
                navArgument("password") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val code = backStackEntry.arguments?.getString("code") ?: ""
            val password = backStackEntry.arguments?.getString("password") ?: ""
            ConfirmScreen(email, code, password)
        }
    }
}
