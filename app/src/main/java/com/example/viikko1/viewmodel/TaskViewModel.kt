package com.example.viikko1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.viikko1.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.viikko1.model.mockTasks


class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    init {
        _tasks.value = mockTasks
    }

    fun addTask(task: Task) {
        _tasks.value = _tasks.value + task
    }

    fun toggleDone(taskId: Int) {
        _tasks.value = _tasks.value.map {
            if (it.id == taskId) it.copy(done = !it.done) else it
        }
    }

    fun removeTask(taskId: Int) {
        _tasks.value = _tasks.value.filterNot { it.id == taskId }
    }

    fun updateTask(updatedTask: Task) {
        _tasks.value = _tasks.value.map {
            if (it.id == updatedTask.id) updatedTask else it
        }
    }
}
