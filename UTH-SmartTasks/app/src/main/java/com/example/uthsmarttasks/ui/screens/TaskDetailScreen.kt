// Tạo file mới: ui/screens/TaskDetailScreen.kt
package com.example.uthsmarttasks.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.uthsmarttasks.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    viewModel: TaskViewModel,
    navController: NavController,
    taskId: String
) {
    val context = LocalContext.current

    // Lắng nghe State từ ViewModel
    val task by viewModel.selectedTask.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    // Gọi API một lần duy nhất khi taskId thay đổi
    LaunchedEffect(taskId) {
        viewModel.getTaskDetail(taskId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chi tiết công việc") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                    }
                },
                actions = {
                    // Yêu cầu: Nút Xóa
                    IconButton(onClick = {
                        viewModel.deleteTask(taskId) {
                            // Callback khi xóa thành công
                            Toast.makeText(context, "Đã xóa công việc!", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        }
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Xóa")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else if (task == null) {
                Text("Không tìm thấy chi tiết công việc.")
            } else {
                // Hiển thị chi tiết
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text("Tiêu đề: ${task!!.title}", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Trạng thái: ${task!!.status}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Ưu tiên: ${task!!.priority}")
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Mô tả: ${task!!.description}")
                }
            }
        }
    }
}