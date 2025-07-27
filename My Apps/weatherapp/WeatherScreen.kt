package com.example.weatherapp

// WeatherViewModel.kt
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState


@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel()) {
    // Observe state from ViewModel (can be Loading, Success, Error)
    val state by viewModel.state

    // Local state to hold the city name input
    var city by remember { mutableStateOf("London") }

    // This effect runs ONCE when the composable is first composed
    LaunchedEffect(Unit) {
        viewModel.fetchWeather(city)  // Automatically fetch weather for default city
    }

    // UI layout
    Column(
        modifier = Modifier
            .fillMaxSize() // Take full screen
            .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()), // Add padding below status bar
        horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
    ) {

        // Text input field for entering the city name
        OutlinedTextField(
            value = city, // Binds to the local `city` state
            onValueChange = { city = it }, // Update the state when user types
            label = { Text("City") }, // Label shown inside the text field

            // Trailing search icon button inside the text field
            trailingIcon = {
                IconButton(onClick = {
                    viewModel.fetchWeather(city) // When clicked, fetch weather
                }) {
                    Icon(Icons.Filled.Search, "Search") // Search icon
                }
            }
        )

        Spacer(Modifier.height(16.dp)) // Adds vertical space between components

        // Based on current state, show appropriate UI
        when (val currentState = state) {
            is WeatherState.Loading -> {
                CircularProgressIndicator() // Show loading spinner
                Text("Loading...") // Loading label
            }

            is WeatherState.Success -> {
                // Pass weather data to a separate UI composable
                WeatherContent(currentState)
            }

            is WeatherState.Error -> {
                // Display error message
                Text("Error: ${currentState.message}", color = Color.Red)

                // Retry button to re-fetch weather
                Button(onClick = { viewModel.fetchWeather(city) }) {
                    Text("Retry")
                }
            }
        }
    }
}