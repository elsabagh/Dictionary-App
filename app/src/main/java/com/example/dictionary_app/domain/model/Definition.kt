package com.example.dictionary_app.domain.model


import com.google.gson.annotations.SerializedName

data class Definition(
    @SerializedName("definition")
    val definition: String,
    @SerializedName("example")
    val example: String

)