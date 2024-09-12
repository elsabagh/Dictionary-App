package com.example.dictionary_app.domain.repository

import com.example.dictionary_app.domain.model.WordItem
import com.example.dictionary_app.util.Result
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {
    suspend fun getWordResult(
        word: String
    ): Flow<Result<WordItem>>
}