package com.example.weatherapp

sealed class WeatherState {
    data object Loading : WeatherState()
    data class Success(
        val city: String,
        val temp: Double,
        val condition: String,
        val forecast: List<DailyForecast>
    ) : WeatherState()
    data class Error(val message: String) : WeatherState()
}

data class DailyForecast(
    val day: String,
    val high: Double,
    val low: Double,
    val icon: String
)