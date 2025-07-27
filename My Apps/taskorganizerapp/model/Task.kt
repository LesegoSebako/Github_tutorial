package com.example.taskorganizerapp.model

data class Task(
    val id: Int,
    var title: String,
    var category: String,
    var isCompleted: Boolean,
    val description: String
)