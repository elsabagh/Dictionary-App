package com.example.dictionary_app.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dictionary_app.domain.model.WordItem

@Composable
fun WordResult(wordItem: WordItem) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 32.dp),
    ) {
        items(wordItem.meanings.size) { index ->
            Meaning(
                meaning = wordItem.meanings[index],
                index = index
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
