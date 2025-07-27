package com.example.taskorganizerapp.navigation

sealed class Screen(val route: String){
    object TaskList : Screen("task_list")
    object AddTask : Screen("add_task")
    object EditTask : Screen( "edit_task/{taskId"){
        fun createdRoute(taskId: Int) = "edit_task/$taskId"
    }
    object Filter : Screen("filter")

}