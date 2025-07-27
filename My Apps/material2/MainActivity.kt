package com.example.material2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.material2.navigation.AppNavigation
import com.example.material2.ui.theme.Material2Theme // Import your custom theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Wrap the entire content inside the Material2Theme
            Material2Theme {
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }
}
