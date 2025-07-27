package com.example.taskorganizerapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskorganizerapp.navigation.Destinations
import com.example.taskorganizerapp.model.Task
import com.example.taskorganizerapp.navigation.Screen


@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp).padding(top = 50.dp)) {
        Text("Welcome to Task Organizer", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate(Destinations.Edit) }) {
            Text("Edit Task")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.navigate(Destinations.Filter) }) {
            Text("Filter Tasks")

            Button(onClick = { navController.navigate(Screen.AddTask.route) }) {
                Text("Add New Task")
            }
        }
    }
}
