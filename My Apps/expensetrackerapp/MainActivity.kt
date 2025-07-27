package com.example.expensetrackerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensetrackerapp.ui.theme.ExpenseTrackerAppTheme
import com.example.expensetrackerapp.viewmodel.ExpenseViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpenseTrackerAppTheme {
                ExpenseScreen()
               Surface(
                   modifier = Modifier.fillMaxSize(),
                   color  = MaterialTheme.colorScheme.background
               ){

                   val application = (this@MainActivity.application)
                   val viewModel: ExpenseViewModel = viewModel(
                       factory = ExpenseViewModelFactory(application)
                   )
               }
            }
        }
    }
}

