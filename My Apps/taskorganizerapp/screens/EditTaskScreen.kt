package com.example.taskorganizerapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskorganizerapp.TaskRepository
import com.example.taskorganizerapp.model.Task


@Composable
fun EditTaskScreen(
    navController: NavController,
    taskId: Int?,
    onSave: (Task) -> Unit

){

    var task by remember { mutableStateOf<Task?>(null) }
    var title by remember { mutableStateOf(task?.title ?: "") }
    var category by remember { mutableStateOf(task?.category ?: "") }
    var completed by remember { mutableStateOf(task?.isCompleted ?: false) }

    LaunchedEffect(taskId) {
        task = TaskRepository.getTaskById(taskId ?: -1)
        task?.let {
            title = it.title
            category = it.category
            completed = it.isCompleted
        }
    }

    Column(modifier = Modifier.padding(16.dp)){
        Text("Edit Task", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = title, onValueChange = {title = it}, label = {Text("Task Title")})
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = category, onValueChange = {category = it}, label = {Text("Category")})
        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Checkbox(checked = completed, onCheckedChange = {completed = it })
            Text("Completed")

        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            task?.let {
                val updated = it.copy(title = title, category = category, isCompleted = completed)
                TaskRepository.updateTask(updated)
                onSave(updated)
            }
            navController.popBackStack()
        }){
            Text("Save Changes")
        }

    }
}

