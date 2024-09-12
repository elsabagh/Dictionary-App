package com.example.dictionary_app.data.dto


import com.google.gson.annotations.SerializedName

data class WordItemDto(
    val meanings: List<MeaningDto> ? = null,
    val phonetic: String ? = null,
    val word: String ? = null
)