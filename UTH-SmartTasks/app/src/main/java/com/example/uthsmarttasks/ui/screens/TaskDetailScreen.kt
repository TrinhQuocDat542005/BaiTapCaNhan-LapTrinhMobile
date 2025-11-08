// Trong file: ui/screens/TaskDetailScreen.kt
package com.example.uthsmarttasks.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Attachment
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Autorenew
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uthsmarttasks.model.Attachment
import com.example.uthsmarttasks.model.Subtask
import com.example.uthsmarttasks.model.Task
import com.example.uthsmarttasks.viewmodel.TaskViewModel
import java.lang.Exception

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    taskId: String,
    viewModel: TaskViewModel,
    navController: NavController
) {
    // Lấy state từ ViewModel
    val task by viewModel.selectedTask.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    // Biến state để hiện dialog xác nhận xóa
    var showDeleteDialog by remember { mutableStateOf(false) }

    // Gọi API để lấy chi tiết task khi màn hình được mở
    LaunchedEffect(taskId) {
        viewModel.getTaskDetail(taskId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Detail",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                },
                // Nút quay lại
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                    }
                },
                // Nút xóa
                actions = {
                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Xóa",
                            tint = Color(0xFFFFA726) // Màu cam
                        )
                    }
                }
            )
        }
    ) { padding ->
        // Nội dung chính
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else if (task != null) {
                // Khi đã có data
                TaskDetailContent(task = task!!)
            } else {
                // Nếu task null (lỗi)
                Text("Không tìm thấy công việc.")
            }
        }
    }

    // Dialog xác nhận xóa
    if (showDeleteDialog) {
        DeleteConfirmationDialog(
            onConfirm = {
                showDeleteDialog = false
                viewModel.deleteTask(taskId) {
                    navController.popBackStack() // Quay lại TaskListScreen sau khi xóa
                }
            },
            onDismiss = {
                showDeleteDialog = false
            }
        )
    }
}

@Composable
private fun TaskDetailContent(task: Task) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Cho phép cuộn
            .padding(horizontal = 16.dp)
    ) {
        // 1. Tiêu đề
        Text(
            text = task.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = task.description,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(24.dp))

        // 2. Thẻ Card màu hồng (Info Card)
        InfoCard(task)
        Spacer(modifier = Modifier.height(24.dp))

        // 3. Danh sách Subtasks
        if (task.subtasks.isNotEmpty()) {
            Text(
                text = "Subtasks",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                task.subtasks.forEach { subtask ->
                    SubtaskItem(subtask)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        // 4. Danh sách Attachments
        if (task.attachments.isNotEmpty()) {
            Text(
                text = "Attachments",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                task.attachments.forEach { attachment ->
                    AttachmentItem(attachment)
                }
            }
        }
    }
}

// Composable cho Card Category, Status, Priority
@Composable
private fun InfoCard(task: Task) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFCE7E9) // Màu hồng nhạt
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            InfoItem(
                icon = Icons.Outlined.GridView,
                title = "Category",
                value = task.category
            )
            InfoItem(
                icon = Icons.Outlined.Autorenew,
                title = "Status",
                value = task.status
            )
            InfoItem(
                icon = Icons.Outlined.Flag,
                title = "Priority",
                value = task.priority
            )
        }
    }
}

// Composable cho 1 mục (Category, Status, Priority)
@Composable
private fun InfoItem(icon: ImageVector, title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, contentDescription = title, tint = Color.Black)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = title, fontSize = 12.sp, color = Color.Gray)
        Text(text = value, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}

// Composable cho 1 mục Subtask
@Composable
private fun SubtaskItem(subtask: Subtask) {
    var isChecked by remember(subtask.isCompleted) {
        mutableStateOf(subtask.isCompleted)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )
            Text(
                text = subtask.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// Composable cho 1 mục Attachment
@Composable
private fun AttachmentItem(attachment: Attachment) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Attachment,
                contentDescription = "Attachment",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = attachment.fileName,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// Composable cho Dialog xác nhận xóa
@Composable
private fun DeleteConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Xác nhận xóa") },
        text = { Text("Bạn có chắc chắn muốn xóa công việc này không?") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Xóa")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Hủy")
            }
        }
    )
}