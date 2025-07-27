package com.example.packinglistapp.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.packinglistapp.screens.MainScreen
import com.example.packinglistapp.screens.ListScreen



@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("list") { ListScreen(navController) }
    }
}
