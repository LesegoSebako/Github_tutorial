package com.example.taskorganizerapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskorganizerapp.TaskRepository

@Composable
fun FilterScreen(
    navController: NavController,
    onFilter: (String, Boolean?) -> Unit
) {
    var selectedCategory by remember { mutableStateOf("") }
    var showCompleted by remember { mutableStateOf<Boolean?>(null) }

    val categories = TaskRepository.getAllTasks().map { it.category }.distinct()

    Column(modifier = Modifier.padding(16.dp).padding(top = 50.dp)) {
        Text("Filter Tasks", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Category")
        categories.forEach { category ->
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                RadioButton(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(category)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Status")
        Row {
            RadioButton(selected = showCompleted == null, onClick = { showCompleted = null })
            Spacer(modifier = Modifier.width(8.dp))
            Text("All")
        }
        Row {
            RadioButton(selected = showCompleted == true, onClick = { showCompleted = true })
            Spacer(modifier = Modifier.width(8.dp))
            Text("Completed")
        }
        Row {
            RadioButton(selected = showCompleted == false, onClick = { showCompleted = false })
            Spacer(modifier = Modifier.width(8.dp))
            Text("Not Completed")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            onFilter(selectedCategory, showCompleted)
            navController.popBackStack()
        }) {
            Text("Apply Filter")
        }
    }
}
