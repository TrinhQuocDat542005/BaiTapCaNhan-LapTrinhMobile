// Tạo file mới, ví dụ: ui/Screen.kt
package com.example.uthsmarttasks.ui

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object TaskList : Screen("task_list")
    object TaskDetail : Screen("task_detail")
    object Profile : Screen("profile")
}