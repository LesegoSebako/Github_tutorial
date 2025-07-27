package com.example.packinglistapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.packinglistapp.navigation.PackingData

@Composable
fun ListScreen(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Full Packing List", style = MaterialTheme.typography.titleLarge)

        for (i in PackingData.items.indices) {
            Text("${PackingData.items[i]} (${PackingData.categories[i]}): ${PackingData.quantities[i]} - ${PackingData.comments[i]}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Items with Quantity >= 2", style = MaterialTheme.typography.titleMedium)

        for (i in PackingData.items.indices) {
            if (PackingData.quantities[i] >= 2) {
                Text("${PackingData.items[i]}: ${PackingData.quantities[i]}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("main") }) {
            Text("Back to Main")
        }
    }
}
