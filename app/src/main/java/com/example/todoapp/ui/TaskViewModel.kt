package com.example.todoapp.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.todoapp.model.Priority
import com.example.todoapp.model.Task
import com.example.todoapp.model.TaskUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    fun addTask(newTask: String, priority: Priority) {
        _uiState.value.list.add(Task(newTask, priority, false))
        _uiState.update { currentState ->
            currentState.copy(
                list = currentState.list
            )
        }
    }

    fun deleteTask(deleteTask: Task) {
        _uiState.value.list.remove(deleteTask)
        val updatedList = _uiState.value.list

        _uiState.update { currentState ->
            currentState.copy(
                list = updatedList
            )
        }
    }

    fun doneTask(doneTask: Task) {
        val newTask = Task(doneTask.task, doneTask.priority, !doneTask.done)
        _uiState.value.list.remove(doneTask)
        _uiState.value.list.add(newTask)
        val updatedList = _uiState.value.list

        _uiState.update { currentState ->
            currentState.copy(
                list = updatedList
            )
        }
    }

    fun resetList() {
        _uiState.update { currentState ->
            currentState.copy(list = mutableStateListOf()) }
    }
}