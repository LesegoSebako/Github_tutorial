package com.example.weatherapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun WeatherContent(data: WeatherState.Success) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(data.city, style = typography.headlineMedium)
            Text("${data.temp}°C", style = typography.displaySmall)
            Text(data.condition, style = typography.titleMedium)
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text("5-Day Forecast", style = MaterialTheme.typography.titleLarge)
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(data.forecast) { day ->
            ForecastDay(day)
        }
    }
}

@Composable
fun ForecastDay(day: DailyForecast) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn() + slideInHorizontally()
    ) {
        Card(modifier = Modifier.width(100.dp)) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(day.day)
                Text("H: ${day.high}°")
                Text("L: ${day.low}°")
                Text(day.icon)

            }
        }
    }
}