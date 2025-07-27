package com.example.taskorganizerapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskorganizerapp.model.Task
import com.example.taskorganizerapp.screens.*
import com.example.taskorganizerapp.screens.FilterScreen

object Destinations {
    const val Home = "home"
    const val Edit = "edit"
    const val Filter = "filter"
}

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
    onSaveTask: (Task) -> Unit,
    onFilterApply: (String, Boolean?) -> Unit
) {
    NavHost(navController = navController, startDestination = Destinations.Home) {
        composable(Destinations.Home) {
            HomeScreen(navController)
        }
        composable(Screen.AddTask.route) {
            AddTaskScreen(navController)
        }
        composable("${Destinations.Edit}/{taskId}",
            arguments = listOf(navArgument("taskId") {
                type = NavType.IntType
                defaultValue = -1
            })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: -1
            EditTaskScreen(navController, taskId, onSaveTask)
        }
        composable(Destinations.Filter) {
            FilterScreen(navController, onFilterApply)
        }

    }
}
