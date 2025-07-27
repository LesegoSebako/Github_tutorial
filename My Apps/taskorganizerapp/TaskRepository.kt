package com.example.taskorganizerapp

import com.example.taskorganizerapp.model.Task

object TaskRepository {
    private val taskList = mutableListOf<Task>()
    private var nextId = 1

    fun getAllTasks(): List<Task> = taskList

    fun addTask(name: String, category: String, isCompleted: Boolean, description: String) {
        val task = Task(id = nextId++, title = name, category = category, isCompleted = isCompleted, description = description)
        taskList.add(task)
    }

    fun getTaskById(id: Int): Task? = taskList.find { it.id == id }

    fun updateTask(updatedTask: Task) {
        val index = taskList.indexOfFirst { it.id == updatedTask.id }
        if (index != -1) {
            taskList[index] = updatedTask
        }
    }

    fun deleteTask(id: Int) {
        taskList.removeAll { it.id == id }
    }

    fun filterTasks(category: String?, showCompleted: Boolean?): List<Task> {
        return taskList.filter {
            (category == null || it.category == category) &&
                    (showCompleted == null || it.isCompleted == showCompleted)
        }
    }
}
