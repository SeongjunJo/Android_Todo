package com.example.todoapp.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.todoapp.data.Database

data class TaskUiState (
    val list: SnapshotStateList<Task> = mutableStateListOf(
        *Database.TodoList.toTypedArray()
    )
)