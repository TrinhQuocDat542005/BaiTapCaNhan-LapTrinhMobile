// Trong file: model/Task.kt
package com.example.uthsmarttasks.model

data class Task(
    val id: Int, // JSON là Int (94, 95), không phải String
    val title: String,
    val description: String,
    val status: String,
    val priority: String,
    val category: String,
    val dueDate: String,
    val createdAt: String,
    val updatedAt: String,
    val subtasks: List<Subtask>,
    val attachments: List<Attachment>,
    val reminders: List<Reminder>
)