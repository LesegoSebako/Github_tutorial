package com.example.material.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.material.screens.LoginScreen
import com.example.material.screens.DashboardScreen


@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = "login"){
        composable("login") {
            LoginScreen(navController)}
        composable("dashboard") {
            DashboardScreen()}
    }
}