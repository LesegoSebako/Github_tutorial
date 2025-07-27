package com.example.material2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.material2.ui.theme.Material2Theme

@Composable
fun DetailsScreen(itemId: Int) {
    // Wrap screen UI in Material2Theme
    Material2Theme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Details Screen",
                style = MaterialTheme.typography.h6 // Use from standard MaterialTheme
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Item ID: $itemId",
                style = MaterialTheme.typography.body1
            )
        }
    }
}
