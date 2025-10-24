package com.example.btvn01.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.btvn01.models.*
import com.example.btvn01.ui.components.BookCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen() {
    // ✅ Danh sách sách mẫu
    val allBooks = listOf(
        Book(101, "Lập trình Kotlin cơ bản", "Nguyễn Văn A"),
        Book(102, "Jetpack Compose nâng cao", "Trần Thị B"),
        Book(103, "Học Android Studio", "Lê Văn C")
    )

    // ✅ Danh sách sinh viên mẫu
    val students = listOf(
        Student(1, "Trịnh Quốc Đạt").apply {
            borrowBook(allBooks[0])
            borrowBook(allBooks[1])
        },
        Student(2, "Phan Phát Đạt").apply {
            borrowBook(allBooks[0])
        },
        Student(3, "Nguyễn Quyền")
    )

    var studentName by remember { mutableStateOf("") }
    var foundStudent by remember { mutableStateOf<Student?>(null) }
    var borrowedBooks by remember { mutableStateOf<List<Book>>(emptyList()) }
    var message by remember { mutableStateOf("") }

    // 🔸 Dialog thêm sách
    var showDialog by remember { mutableStateOf(false) }
    var newBookTitle by remember { mutableStateOf("") }
    var newBookAuthor by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("📚 Hệ thống Quản lý Thư viện") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (foundStudent != null) {
                        showDialog = true
                    } else {
                        message = "Vui lòng tìm sinh viên trước khi thêm sách"
                    }
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Text("+", color = Color.White)
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = studentName,
                onValueChange = { studentName = it },
                label = { Text("Nhập tên sinh viên") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val student = students.find {
                        it.getFullName().equals(studentName, ignoreCase = true)
                    }

                    if (student != null) {
                        foundStudent = student
                        borrowedBooks = student.getBorrowedBooks()
                        message = if (borrowedBooks.isEmpty()) {
                            "Bạn chưa mượn quyển sách nào"
                        } else ""
                    } else {
                        foundStudent = null
                        borrowedBooks = emptyList()
                        message = "Không tìm thấy sinh viên"
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Tìm")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Danh sách sách:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            if (message.isNotEmpty()) {
                Text(text = message, color = Color.Gray, fontStyle = FontStyle.Italic)
            } else {
                LazyColumn {
                    items(borrowedBooks) { book ->
                        BookCard(
                            book = book,
                            onBorrowClick = {},
                            onReturnClick = {}
                        )
                    }
                }
            }
        }
    }

    // 🔸 Dialog thêm sách mới
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Thêm sách mới") },
            text = {
                Column {
                    OutlinedTextField(
                        value = newBookTitle,
                        onValueChange = { newBookTitle = it },
                        label = { Text("Tên sách") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = newBookAuthor,
                        onValueChange = { newBookAuthor = it },
                        label = { Text("Tác giả") }
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    if (newBookTitle.isNotBlank() && newBookAuthor.isNotBlank() && foundStudent != null) {
                        val newBook = Book(
                            id = (100..999).random(),
                            title = newBookTitle,
                            author = newBookAuthor
                        )
                        foundStudent!!.borrowBook(newBook)
                        borrowedBooks = foundStudent!!.getBorrowedBooks()
                    }
                    showDialog = false
                    newBookTitle = ""
                    newBookAuthor = ""
                }) {
                    Text("Thêm")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Hủy")
                }
            }
        )
    }
}
