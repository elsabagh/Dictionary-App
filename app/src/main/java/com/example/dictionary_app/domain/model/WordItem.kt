package com.example.dictionary_app.domain.model


data class WordItem(
    val meanings: List<Meaning>,
    val phonetic: String,
    val word: String
)