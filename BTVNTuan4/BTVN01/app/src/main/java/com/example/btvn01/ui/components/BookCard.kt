package com.example.btvn01.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.btvn01.models.Book

@Composable
fun BookCard(
    book: Book,
    onBorrowClick: () -> Unit,
    onReturnClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "📖 ${book.getTitle()}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Tác giả: ${book.getAuthor() ?: "Không rõ"}")
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = onReturnClick) {
                    Text("Trả")
                }
            }
        }
    }
}
