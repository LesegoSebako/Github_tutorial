package com.example.material2.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    val items = listOf(101, 102, 103)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text("Home Screen", style = Material2Theme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        items.forEach { id ->
            Text(
                text = "Item ID: $id",
                style = Material2Theme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("details/$id")
                    }
                    .padding(8.dp)
            )
        }
    }
}
