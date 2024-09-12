package com.example.dictionary_app.data.dto


import com.google.gson.annotations.SerializedName

data class MeaningDto(
    @SerializedName("definitions")
    val definitions: List<DefinitionDto>? = null,
    @SerializedName("partOfSpeech")
    val partOfSpeech: String? = null

)