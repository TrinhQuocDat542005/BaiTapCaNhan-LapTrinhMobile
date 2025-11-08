// Tạo file mới: ui/screens/composables/TaskItem.kt
package com.example.uthsmarttasks.ui.screens.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uthsmarttasks.model.Task

// Hàm để lấy màu dựa trên Trạng thái (Status)
@Composable
private fun getTaskColor(status: String): Color {
    return when (status) {
        "In Progress" -> Color(0xFFFADADF) // Màu hồng nhạt
        "Pending" -> Color(0xFFD6F0E0) // Màu xanh lá nhạt
        "Completed" -> Color(0xFFD1E8FF) // Màu xanh dương nhạt
        else -> MaterialTheme.colorScheme.surfaceVariant
    }
}

@Composable
fun TaskItem(task: Task, onClick: () -> Unit) {
    var isChecked by remember { mutableStateOf(task.status == "Completed") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = getTaskColor(task.status)
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = task.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = task.description,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Hàng Status và Date
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Status: ${task.status}",
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                    Text(
                        text = task.dueDate.substringBefore("T"), // Chỉ lấy ngày, bỏ giờ
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}