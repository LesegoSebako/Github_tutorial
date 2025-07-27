package com.example.notekeeper.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notekeeper.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(noteId: String, viewModel: NoteViewModel, navController: NavController) {
    val note = viewModel.getNoteById(noteId)

    // If the note is null, show a fallback message
    if (note == null) {
        Text("Note not found", modifier = Modifier.padding(16.dp))
        return
    }

    // Correct Scaffold syntax
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(note.title) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.deleteNote(noteId)
                        navController.navigateUp()
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            )
        }
    ) { padding ->
        // UI content of the screen
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Title: ${note.title}", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Content: ${note.content}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Created: ${
                    SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(
                        Date(note.timestamp)
                    )
                }",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}
