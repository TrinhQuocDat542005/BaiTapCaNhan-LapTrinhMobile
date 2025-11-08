// Trong file: network/ApiService.kt
package com.example.uthsmarttasks.network

// 1. Thêm 2 import này
import com.example.uthsmarttasks.model.TaskDetailResponse
import com.example.uthsmarttasks.model.TaskResponse
// ---
import com.example.uthsmarttasks.model.Task
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("tasks")
    suspend fun getTasks(): TaskResponse // 2. Sửa ở đây

    @GET("task/{id}")
    suspend fun getTaskDetail(@Path("id") taskId: String): TaskDetailResponse // 3. Sửa ở đây

    @DELETE("task/{id}")
    suspend fun deleteTask(@Path("id") taskId: String): Response<Unit>
}