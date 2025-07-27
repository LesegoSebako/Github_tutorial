package com.example.diaryapp.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryScreen() {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var noteText by remember { mutableStateOf("") }
    val notes = remember { mutableStateMapOf<String, String>() }

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val selectedDateString = selectedDate.format(formatter)

    var showDatePicker by remember { mutableStateOf(false) }

    // Create and remember DatePickerState
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedDate.atStartOfDay(ZoneId.systemDefault())
            .toInstant().toEpochMilli()
    )

    Column(modifier = Modifier
        .padding(WindowInsets.systemBars.asPaddingValues())
        .padding(16.dp)) {
        Text("Diary", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Show button to pick date
        Button(onClick = { showDatePicker = true }) {
            Text("Pick Date: $selectedDateString")
        }

        // Show DatePickerDialog if triggered
        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        showDatePicker = false
                        val selectedMillis = datePickerState.selectedDateMillis
                        if (selectedMillis != null) {
                            val localDate = Instant.ofEpochMilli(selectedMillis)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                            selectedDate = localDate
                            noteText = notes[localDate.format(formatter)] ?: ""
                        }
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Note for $selectedDateString", style = MaterialTheme.typography.titleMedium)

        BasicTextField(
            value = noteText,
            onValueChange = { noteText = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(8.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            notes[selectedDateString] = noteText
        }) {
            Text("Save Note")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Saved Notes:", style = MaterialTheme.typography.titleMedium)
        notes.forEach { (date, note) ->
            Text("$date: $note", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
