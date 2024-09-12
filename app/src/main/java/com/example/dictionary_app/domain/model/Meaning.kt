package com.example.dictionary_app.domain.model


import com.google.gson.annotations.SerializedName

data class Meaning(
    val definitions: Definition,
    val partOfSpeech: String

)