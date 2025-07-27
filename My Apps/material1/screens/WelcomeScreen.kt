package com.example.material1.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun WelcomeScreen(navController: NavHostController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text ("Welcome", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val username = "JohnDoe"
            navController.navigate("profile/JohnDoe")
        }){
            Text(text = "Go to profile")
        }
    }
}