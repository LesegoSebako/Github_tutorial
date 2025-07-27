package com.example.packinglistapp.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.packinglistapp.navigation.PackingData
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import kotlin.system.exitProcess


@Composable
fun MainScreen(navController: NavHostController) {
    var itemName by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Add to Packing List", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Item Name") })
        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") })
        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Quantity") })
        OutlinedTextField(
            value = comment,
            onValueChange = { comment = it },
            label = { Text("Comments") }
        )

        if (errorMessage.isNotBlank()) {
            Text(errorMessage, color = Color.Red)

        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            val quantityInt = quantity.toIntOrNull()

            if (itemName.isBlank() || category.isBlank() || quantityInt == null) {
                errorMessage = "Please fill in all the fields correctly."
            } else {
                PackingData.items.add(itemName)
                PackingData.categories.add(category)
                PackingData.quantities.add(quantityInt)
                PackingData.comments.add(comment)
                errorMessage = ""
                itemName = ""
                category = ""
                quantity = ""
                comment = ""
            }
        }) {
            Text("Add to Packing List")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = { navController.navigate("list") }) {
            Text("View Packing List")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {exitProcess(0)}) {
            Text("Exit App")
        }

    }
}
