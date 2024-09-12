package com.example.dictionary_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionary_app.domain.repository.DictionaryRepository
import com.example.dictionary_app.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dictionaryRepository: DictionaryRepository
) : ViewModel() {

    // StateFlow to hold the current state of the UI
    private val _mainState = MutableStateFlow(MainState())
    val mainState = _mainState.asStateFlow()

    // Job for managing search coroutine
    private var searchJob: Job? = null

    // Initialize ViewModel
    init {
        // Set an initial search word in the state
        _mainState.update {
            it.copy(searchWord = "Word")
        }

        // Cancel any existing search job if it exists
        searchJob?.cancel()

        // Launch a coroutine to load the word result
        searchJob = viewModelScope.launch {
            loadWordResult()
        }
    }

    // Handle UI events
    fun onEvent(mainUiEvents: MainUiEvents) {
        when (mainUiEvents) {
            // When the search button is clicked
            MainUiEvents.OnSearchWordClick -> {
                // Cancel any existing search job
                searchJob?.cancel()

                // Launch a new coroutine to load the word result
                searchJob = viewModelScope.launch {
                    loadWordResult()
                }
            }

            // When the search word changes
            is MainUiEvents.OnSearchWordChange -> {
                // Update the search word in the state
                _mainState.update {
                    it.copy(
                        searchWord = mainUiEvents.newWord.lowercase()
                    )
                }
            }
        }
    }

    // Load the word result from the repository
    private fun loadWordResult() {
        viewModelScope.launch {
            // Collect the result from the repository
            dictionaryRepository.getWordResult(
                _mainState.value.searchWord
            ).collect { result ->
                when (result) {
                    // Handle error case (no action taken here)
                    is Result.Error -> Unit

                    // Handle loading state
                    is Result.Loading -> {
                        _mainState.update {
                            it.copy(
                                isLoading = result.isLoading
                            )
                        }
                    }

                    // Handle success case
                    is Result.Success -> {
                        result.data?.let { wordItem ->
                            // Update the state with the fetched word item
                            _mainState.update {
                                it.copy(
                                    wordItem = wordItem
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}