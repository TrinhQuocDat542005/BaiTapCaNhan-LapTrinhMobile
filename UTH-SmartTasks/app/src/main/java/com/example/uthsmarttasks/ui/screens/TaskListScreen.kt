
package com.example.uthsmarttasks.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.uthsmarttasks.viewmodel.TaskViewModel
import com.example.uthsmarttasks.ui.screens.composables.EmptyTaskScreen
import com.example.uthsmarttasks.ui.screens.composables.TaskItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    viewModel: TaskViewModel,
    navController: NavController
) {
    val tasks by viewModel.tasks.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getTasks()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("UTH SmartTasks") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { /* TODO: Về Home */ }) {
                        Icon(Icons.Default.Home, contentDescription = "Trang chủ")
                    }
                    IconButton(onClick = { /* TODO: Tới màn hình 'Completed' */ }) {
                        Icon(Icons.Default.List, contentDescription = "Hoàn thành")
                    }

                    IconButton(onClick = { navController.navigate("profile") }) {
                        Icon(Icons.Default.Person, contentDescription = "Cá nhân")
                    }
                },

                floatingActionButton = {
                    FloatingActionButton(onClick = { /* TODO: Thêm Task mới */ }) {
                        Icon(Icons.Default.Add, contentDescription = "Thêm công việc")
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
            } else if (tasks.isEmpty()) {

                EmptyTaskScreen()
            } else {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(tasks) { task ->
                        TaskItem(
                            task = task,
                            onClick = {
                                navController.navigate("task_detail/${task.id}")
                            }
                        )
                    }
                }
            }
        }
    }
}