package com.example.grocerylistapp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



@Composable
fun QuantityStepper(current: Int, onIncrease: () -> Unit, onDecrease: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onDecrease) {
            Icon(
                imageVector = Icons.Default.Remove,  // Or Icons.Filled.Remove
                contentDescription = "Decrease quantity"
            )
        }
        Text(
            text = "$current",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        IconButton(onClick = onIncrease) {
            Icon(
                imageVector = Icons.Default.Add,  // Or Icons.Filled.Add
                contentDescription = "Increase quantity"
            )
        }
    }
}

@Composable
fun IconTest() {
    Icon(Icons.Filled.Remove, "Test") // Does this show an error?
}