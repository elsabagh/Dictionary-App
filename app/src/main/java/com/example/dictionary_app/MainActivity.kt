package com.example.dictionary_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dictionary_app.presentation.MainViewModel
import com.example.dictionary_app.ui.screen.BarColor
import com.example.dictionary_app.ui.screen.MainScreen
import com.example.dictionary_app.ui.screen.SearchBar
import com.example.dictionary_app.ui.theme.DictionaryAppTheme
import dagger.hilt.android.AndroidEntryPoint

// Marks the MainActivity class as an entry point for dependency injection using Hilt
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables edge-to-edge mode, useful for modern UI layouts
        enableEdgeToEdge()

        // Sets the UI content of the activity using Jetpack Compose
        setContent {
            // Applies the app theme to the entire content
            DictionaryAppTheme {
                // Sets the system bar color
                BarColor()

                // Obtains the ViewModel instance using Hilt
                val mainViewModel = hiltViewModel<MainViewModel>()

                // Collects the current state from the ViewModel (search word, loading status, etc.)
                val mainState by mainViewModel.mainState.collectAsState()

                // Scaffold is used to organize the layout of the screen
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    // Defines the top bar as the search bar
                    topBar = { SearchBar(mainState, mainViewModel) }
                ) { paddingValues ->
                    // Main content of the screen, including search results and loading indicator
                    MainScreen(mainState, paddingValues)
                }
            }
        }
    }
}