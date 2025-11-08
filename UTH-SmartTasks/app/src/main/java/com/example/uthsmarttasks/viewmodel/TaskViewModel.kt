// Trong file: viewmodel/TaskViewModel.kt
package com.example.uthsmarttasks.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uthsmarttasks.model.Task
import com.example.uthsmarttasks.model.TaskDetailResponse
import com.example.uthsmarttasks.model.TaskResponse
import com.example.uthsmarttasks.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class TaskViewModel : ViewModel() {

    private val api = RetrofitInstance.api

    // State cho màn hình List
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    // State cho trạng thái loading
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // State cho màn hình Detail
    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask

    // 1. Lấy danh sách tasks (Đã sửa để xử lý TaskResponse)
    fun getTasks() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = api.getTasks() // 1. Nhận về TaskResponse
                if (response.isSuccess) {
                    // 2. Lấy list từ "data", nếu null thì trả list rỗng
                    _tasks.value = response.tasks ?: emptyList()
                } else {
                    Log.w("TaskViewModel", "API getTasks báo lỗi: ${response.message}")
                    _tasks.value = emptyList()
                }
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Lỗi khi lấy danh sách tasks!", e)
                _tasks.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    // 2. Lấy chi tiết 1 task (Đã sửa để xử lý TaskDetailResponse)
    fun getTaskDetail(taskId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _selectedTask.value = null // Xóa task cũ đi
            try {
                val response = api.getTaskDetail(taskId)
                if (response.isSuccess) {
                    _selectedTask.value = response.task
                } else {
                    Log.w("TaskViewModel", "API getTaskDetail báo lỗi: ${response.message}")
                    _selectedTask.value = null
                }
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Lỗi khi lấy chi tiết task!", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    // 3. Xóa 1 task
    fun deleteTask(taskId: String, onTaskDeleted: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.deleteTask(taskId)
                if (response.isSuccessful) {
                    onTaskDeleted() // Xóa thành công -> điều hướng về
                } else {
                    Log.w("TaskViewModel", "Xóa task không thành công: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Lỗi khi xóa task!", e)
            }
        }
    }
}