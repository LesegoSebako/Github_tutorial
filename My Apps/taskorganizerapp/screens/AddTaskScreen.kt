package com.example.taskorganizerapp.screens

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskorganizerapp.TaskRepository
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.taskorganizerapp.navigation.Screen


@Composable
fun AddTaskScreen(navController: NavController) {
    var title by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isCompleted by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp).padding(top = 50.dp)) {
        TextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        TextField(value = category, onValueChange = { category = it }, label = { Text("Category") })
        TextField(value = description, onValueChange = { description = it }, label = { Text("Description") })

        Row {
            Checkbox(checked = isCompleted, onCheckedChange = { isCompleted = it })
            Text("Completed")
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Button(onClick = {
            TaskRepository.addTask(title, category, isCompleted, description)
            navController.navigate(Screen.TaskList.route)
        }) {
            Text("Add Task")
        }
    }
}
