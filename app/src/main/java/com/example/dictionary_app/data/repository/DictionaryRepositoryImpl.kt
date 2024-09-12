package com.example.dictionary_app.data.repository

import android.app.Application
import com.example.dictionary_app.R
import com.example.dictionary_app.data.api.DictionaryApi
import com.example.dictionary_app.data.mapper.toWordItem
import com.example.dictionary_app.domain.model.WordItem
import com.example.dictionary_app.domain.repository.DictionaryRepository
import com.example.dictionary_app.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

// Implementation of the DictionaryRepository interface
class DictionaryRepositoryImpl @Inject constructor(
    private val dictionaryApi: DictionaryApi, // API service for dictionary operations
    private val application: Application // Application context for accessing resources
) : DictionaryRepository {

    // Function to fetch word results from the API
    override suspend fun getWordResult(word: String): Flow<Result<WordItem>> {
        return flow {
            emit(Result.Loading(true)) // Emit a loading state before starting the API call

            try {
                // Attempt to fetch the word result from the API
                val remoteWordResult = dictionaryApi.getWordResult(word)

                // Check if the result is not empty
                if (remoteWordResult!!.isNotEmpty()) {
                    val wordItemDto = remoteWordResult[0] // Get the first item from the result
                    emit(Result.Success(wordItemDto!!.toWordItem())) // Convert DTO to domain model and emit success result
                } else {
                    // Handle the case where no results are found
                    emit(Result.Error(application.getString(R.string.can_t_get_result)))
                }

            } catch (e: HttpException) {
                // Handle HTTP-related exceptions
                emit(Result.Error(application.getString(R.string.can_t_get_result)))
            } catch (e: IOException) {
                // Handle network-related exceptions
                emit(Result.Error(application.getString(R.string.can_t_get_result)))
            } catch (e: Exception) {
                // Handle any other unexpected exceptions
                emit(Result.Error(application.getString(R.string.can_t_get_result)))
            } finally {
                // Emit a loading state as false after the API call completes, regardless of the result
                emit(Result.Loading(false))
            }
        }
    }
}