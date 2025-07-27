package com.example.material1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.material1.screens.ProfileScreen
import com.example.material1.screens.WelcomeScreen

import androidx.navigation.navArgument
@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(navController = navController,startDestination = "welcome"){
        composable("welcome"){
            WelcomeScreen(navController)
        }
        composable(
            route = "profile/{username}",
            arguments = listOf(navArgument("username") { defaultValue = "Guest" })
        ) {backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "Guest"
            ProfileScreen(username = username)
        }
    }
}