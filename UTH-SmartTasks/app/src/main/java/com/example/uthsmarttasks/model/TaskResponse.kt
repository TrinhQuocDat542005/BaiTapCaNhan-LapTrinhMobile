// Sửa file: model/TaskResponse.kt
package com.example.uthsmarttasks.model

import com.google.gson.annotations.SerializedName

data class TaskResponse(
    @SerializedName("isSuccess")
    val isSuccess: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val tasks: List<Task>? // Key "data" chứa List<Task>
)