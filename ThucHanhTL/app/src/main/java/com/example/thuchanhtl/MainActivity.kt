package com.example.thuchanhtl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.system.measureTimeMillis

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompareColumnAndLazy()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompareColumnAndLazy() {
    var showLazy by remember { mutableStateOf(true) }
    var loadTime by remember { mutableStateOf(0L) }

    val items = remember { (1..1_000_000).map { "Item $it" } }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("So sánh Column vs LazyColumn") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = {
                    val time = measureTimeMillis {
                        showLazy = true
                    }
                    loadTime = time
                }) {
                    Text("Dùng LazyColumn")
                }

                Button(onClick = {
                    val time = measureTimeMillis {
                        showLazy = false
                    }
                    loadTime = time
                }) {
                    Text("Dùng Column")
                }
            }

            Text(
                text = "Thời gian đổi chế độ: ${loadTime}ms",
                modifier = Modifier.padding(8.dp)
            )

            HorizontalDivider()

            if (showLazy) {
                Text(
                    text = "Hiển thị bằng LazyColumn",
                    modifier = Modifier.padding(8.dp)
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(items) { item ->
                        Text(
                            text = item,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            } else {
                Text(
                    text = "Hiển thị bằng Column (Cẩn thận: Sẽ lag hoặc crash 😅)",
                    modifier = Modifier.padding(8.dp)
                )
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items.forEach { item ->
                        Text(
                            text = item,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            }
        }
    }
}
