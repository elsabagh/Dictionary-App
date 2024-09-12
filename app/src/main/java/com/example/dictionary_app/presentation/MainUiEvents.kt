package com.example.dictionary_app.presentation

sealed class MainUiEvents {
    data class OnSearchWordChange(
        val newWord: String
    ) : MainUiEvents()

    object OnSearchWordClick : MainUiEvents()

}