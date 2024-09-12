package com.example.dictionary_app.presentation

import com.example.dictionary_app.domain.model.WordItem

data class MainState(
    val wordItem: WordItem? = null,
    val isLoading: Boolean = false,
    val searchWord: String = ""
)
