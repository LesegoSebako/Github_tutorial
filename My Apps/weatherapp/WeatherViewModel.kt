package com.example.weatherapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// ViewModel for managing UI state and fetching weather data
class WeatherViewModel : ViewModel() {

    // Private mutable state holding the current weather state (Loading, Success, or Error)
    private val _state = mutableStateOf<WeatherState>(WeatherState.Loading)

    // Public immutable version of the state to expose to the UI (Composable)
    val state: State<WeatherState> = _state

    // Function to fetch weather data for a given city
    fun fetchWeather(city: String) {
        // Launch a coroutine in the ViewModel's lifecycle-aware scope
        viewModelScope.launch {

            // Set the UI state to loading before simulating API call
            _state.value = WeatherState.Loading

            // Simulate network delay to mimic an actual API request
            delay(1500)  // 1.5 seconds

            // Simulate response with dummy data or error
            _state.value = try {
                // Return a fake successful response
                WeatherState.Success(
                    city = city,              // City name from input
                    temp = 22.5,              // Current temperature (mock value)
                    condition = "Sunny",      // Weather condition (mock)
                    forecast = listOf(        // List of daily forecasts (mock)
                        DailyForecast("Mon", 24.0, 18.0, "sunny"),
                        DailyForecast("Tue", 19.0, 14.0, "cloudy")
                    )
                )
            } catch (e: Exception) {
                // In case of any exception, return an error state
                WeatherState.Error("Failed to load")
            }
        }
    }
}
