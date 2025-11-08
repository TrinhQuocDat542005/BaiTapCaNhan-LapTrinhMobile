// Trong file: MainActivity.kt
package com.example.uthsmarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uthsmarttasks.ui.Screen // 👈 THÊM IMPORT
import com.example.uthsmarttasks.ui.screens.* import com.example.uthsmarttasks.ui.theme.UTHSmartTasksTheme
import com.example.uthsmarttasks.viewmodel.TaskViewModel
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private val taskViewModel by viewModels<TaskViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        setContent {
            UTHSmartTasksTheme {
                val navController = rememberNavController()

                // Quyết định màn hình bắt đầu
                val startDestination = if (auth.currentUser != null) {
                    Screen.TaskList.route
                } else {
                    Screen.Login.route
                }

                NavHost(navController = navController, startDestination = startDestination) {
                    // Màn hình Login
                    composable(Screen.Login.route) {
                        LoginScreen( // ✅ SỬA 1: Chỉ truyền onLoginSuccess
                            onLoginSuccess = {
                                navController.navigate(Screen.TaskList.route) {
                                    popUpTo(Screen.Login.route) {
                                        this.inclusive = true // 👈 ✅ SỬA 2: Thêm "this."
                                    }
                                }
                            }
                        )
                    }

                    // Màn hình Danh sách Task (Trang chủ MỚI)
                    composable(Screen.TaskList.route) {
                        TaskListScreen(
                            viewModel = taskViewModel,
                            navController = navController
                        )
                    }

                    // Màn hình Profile (Trang con)
                    composable(Screen.Profile.route) {
                        ProfileScreen(
                            user = auth.currentUser,
                            navController = navController
                        )
                    }

                    // Màn hình Chi tiết Task
                    composable(
                        route = Screen.TaskDetail.route + "/{taskId}",
                        arguments = listOf(navArgument("taskId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val taskId = backStackEntry.arguments?.getString("taskId")
                        if (taskId != null) {
                            TaskDetailScreen(
                                taskId = taskId,
                                viewModel = taskViewModel,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}