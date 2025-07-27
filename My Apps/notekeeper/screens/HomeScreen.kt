package com.example.notekeeper.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notekeeper.model.Note
import com.example.notekeeper.viewmodel.NoteViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: NoteViewModel) {
    val notes by viewModel.notes.collectAsState()
    val context = LocalContext.current

    var title by rememberSaveable { mutableStateOf("") }
    var content by rememberSaveable { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (title.isNotBlank() && content.isNotBlank()) {
                        viewModel.addNote(Note(title = title, content = content))
                        title = ""
                        content = ""
                        Toast.makeText(context, "Note added", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Fill in all fields", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        },
        modifier = Modifier.padding(16.dp)
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Content") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Your Notes", style = MaterialTheme.typography.headlineSmall)

            LazyColumn {
                items(notes) { note ->
                    Card(
                        onClick = { navController.navigate("detail/${note.id}") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(note.title, style = MaterialTheme.typography.titleMedium)
                            Text(
                                note.content,
                                maxLines = 1,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}

