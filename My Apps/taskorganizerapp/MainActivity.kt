
package com.example.taskorganizerapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.taskorganizerapp.navigation.NavigationGraph
import com.example.taskorganizerapp.ui.theme.TaskOrganizerAppTheme
import com.example.taskorganizerapp.model.Task
import com.example.taskorganizerapp.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Define what happens when a task is saved
        val onSaveTask: (Task) -> Unit = { task ->
            TaskRepository.addTask( // or your actual save logic
            name = task.title,
            category = task.category,
            isCompleted = task.isCompleted,
                description = task.description

            )
        }

        // Define what happens when a filter is applied
        val onFilterApply: (String?, Boolean?) -> Unit = { category, isCompleted ->
            TaskRepository.filterTasks(category, isCompleted)
        }
        TaskRepository.addTask(
            name = "Sample Task",
            category = "Work",
            isCompleted = false,
            description = "This is sample task for testing"
        )
        setContent {
            TaskOrganizerAppTheme {
                NavigationGraph(
                    onSaveTask = onSaveTask,
                    onFilterApply = onFilterApply
                )
            }
        }
    }
}
