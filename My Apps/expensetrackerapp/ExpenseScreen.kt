package com.example.expensetrackerapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensetrackerapp.viewmodel.ExpenseViewModel
import com.example.expensetrackerapp.Category
import java.util.Locale

@Composable
fun ExpenseScreen(viewModel: ExpenseViewModel = viewModel()) {
    val categories by viewModel.categories.collectAsState(initial = emptyList())

    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<com.example.expensetrackerapp.Category?>(null)  }
    var expanded by remember { mutableStateOf(false) } // For dropdown state

    Column(Modifier.padding(16.dp)) {
        // Amount TextField
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Description TextField
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        // Category Dropdown
        Box(Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(selectedCategory?.name ?: "Select Category")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category.name) },
                        onClick = {
                            selectedCategory = category
                            expanded = false
                        }
                    )
                }
            }
        }

        // Submit Button
        Button(
            onClick = {
                selectedCategory?.let { category ->
                    viewModel.addExpense(
                        amount = amount.toDoubleOrNull() ?: 0.0,
                        description = description,
                        categoryId = category.id
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = amount.isNotBlank() && selectedCategory != null
        ) {
            Text("Add Expense")
        }
    }
}