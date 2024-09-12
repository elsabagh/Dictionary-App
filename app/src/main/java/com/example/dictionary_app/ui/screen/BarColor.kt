package com.example.dictionary_app.ui.screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

// Composable that sets the system bar color to match the app's theme
@Composable
fun BarColor() {
    // Access the system UI controller
    val systemUiController = rememberSystemUiController()

    // Get the background color from the app's theme
    val color = MaterialTheme.colorScheme.background

    // Set the system bar color as a side effect whenever the color changes
    LaunchedEffect(color) {
        systemUiController.setSystemBarsColor(color)
    }
}