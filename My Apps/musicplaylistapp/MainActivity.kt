package com.example.musicplaylistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.musicplaylistapp.ui.theme.MusicPlaylistAppTheme
import com.example.musicplaylistapp.navigation.AppNavigation
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicPlaylistAppTheme {

                val navController = rememberNavController()
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavigation(navController)
                }
            }
        }
    }
}

