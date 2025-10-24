package com.example.thuchanh2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thuchanh2.screens.HomeScreen
import com.example.thuchanh2.screens.OnboardingScreen
import com.example.thuchanh2.screens.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.SPLASH // Bắt đầu từ Splash
    ) {
        // Màn hình Splash
        composable(AppRoutes.SPLASH) {
            SplashScreen(onSplashFinished = {
                navController.navigate(AppRoutes.ONBOARDING) {
                    popUpTo(AppRoutes.SPLASH) { inclusive = true }
                }
            })
        }

        // Màn hình Onboarding
        composable(AppRoutes.ONBOARDING) {
            OnboardingScreen(onGetStartedClick = {
                navController.navigate(AppRoutes.HOME) {
                    popUpTo(AppRoutes.ONBOARDING) { inclusive = true }
                }
            })
        }

        // Màn hình Home
        composable(AppRoutes.HOME) {
            HomeScreen()
        }
    }
}