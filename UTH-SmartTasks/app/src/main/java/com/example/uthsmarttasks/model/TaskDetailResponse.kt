// Tạo file mới: model/TaskDetailResponse.kt
package com.example.uthsmarttasks.model

import com.google.gson.annotations.SerializedName

data class TaskDetailResponse(
    @SerializedName("isSuccess")
    val isSuccess: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val task: Task? // Key "data" chứa 1 Task
)