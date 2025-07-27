package com.example.packinglistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.packinglistapp.navigation.AppNavigation
import com.example.packinglistapp.screens.ListScreen
import com.example.packinglistapp.screens.MainScreen
import com.example.packinglistapp.ui.theme.PackingListAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PackingListAppTheme {

                val navController = rememberNavController()
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavigation(navController)
                }
            }
        }
    }
}