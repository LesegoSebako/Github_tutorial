package com.example.notekeeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notekeeper.screens.*

import com.example.notekeeper.ui.theme.NoteKeeperTheme
import com.example.notekeeper.viewmodel.NoteViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteKeeperTheme {
                val navController = rememberNavController()
                val viewModel: NoteViewModel = viewModel()

                NavHost(navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(navController, viewModel)
                    }
                    composable("detail/{noteId}") { backStackEntry ->
                        val noteId = backStackEntry.arguments?.getString("noteId") ?: ""
                        NoteDetailScreen(noteId, viewModel, navController)
                    }
                }
            }
        }
    }
}

