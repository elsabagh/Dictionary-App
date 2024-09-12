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

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DictionaryAppTheme {
                BarColor()
                val mainViewModel = hiltViewModel<MainViewModel>()
                val mainState by mainViewModel.mainState.collectAsState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { SearchBar(mainState, mainViewModel) }
                ) { paddingValues ->
                    MainScreen(mainState, paddingValues)
                }
            }
        }
    }
}