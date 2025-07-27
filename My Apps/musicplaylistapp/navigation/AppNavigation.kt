package com.example.musicplaylistapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.musicplaylistapp.screens.MainScreen
import com.example.musicplaylistapp.screens.DetailedViewScreen



@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("list") { DetailedViewScreen(navController) }
    }
}
