package com.example.material2.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.lightColors


private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80
    tertiary = Pink80
)

private val LightColors = lightColors(
    primary = Color(0xFF6200EE), // Define your primary color
    primaryVariant = Color(0xFF3700B3),
    secondary = Color(0xFF03DAC6),
    background = Color(0xFFF5F5F5),
    surface = Color.White,
    error = Color(0xFFB00020),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White

)

@Composable
fun Material2Theme(content: @Composable () -> Unit) {
    // Define your Material2Theme using colors, typography, etc.
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color.Blue,
            secondary = Color.Green
        ),
        typography = Typography,
        content = content
    )
}