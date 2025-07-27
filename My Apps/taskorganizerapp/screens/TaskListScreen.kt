package com.example.taskorganizerapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskorganizerapp.TaskRepository
import com.example.taskorganizerapp.navigation.Screen


@Composable
fun TaskListScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .padding(top = 30.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
            Text("Tasks", style = MaterialTheme.typography.titleLarge)
            IconButton(onClick = {
                navController.navigate(Screen.AddTask.route)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }

        LazyColumn {
            items(TaskRepository.getAllTasks(), key = { it.id }) { task ->
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .padding(top = 80.dp)
                ) {
                    Text(task.title, style = MaterialTheme.typography.titleMedium)
                    Text(task.category, style = MaterialTheme.typography.bodySmall)
                    Text(task.description)

                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(onClick = {
                            navController.navigate(Screen.EditTask.createdRoute(task.id))
                        }) {
                            Text("Edit")
                        }

                        TextButton(onClick = {
                            TaskRepository.deleteTask(task.id)
                        }) {
                            Text("Delete")

                            Button(onClick = { navController.navigate(Screen.AddTask.route) }) {
                                Text("Add New Task")
                            }
                        }
                    }
                }
            }
        }
    }
}