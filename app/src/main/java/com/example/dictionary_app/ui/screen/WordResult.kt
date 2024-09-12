package com.example.dictionary_app.ui.screen


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dictionary_app.domain.model.WordItem

// Composable that renders the list of meanings and results for the word
@Composable
fun WordResult(wordItem: WordItem) {
    // LazyColumn allows for efficient scrolling through a list of meanings
    LazyColumn(
        contentPadding = PaddingValues(vertical = 32.dp),
    ) {
        // Iterates through the list of meanings and displays each one
        items(wordItem.meanings.size) { index ->
            Meaning(
                meaning = wordItem.meanings[index],
                index = index
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
