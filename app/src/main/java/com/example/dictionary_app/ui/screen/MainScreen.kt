package com.example.dictionary_app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dictionary_app.domain.model.Definition
import com.example.dictionary_app.domain.model.WordItem
import com.example.dictionary_app.presentation.MainState

// Main screen function that renders content based on the current state (loading or results)
@Composable
fun MainScreen(
    mainState: MainState,
    paddingValues: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp)
        ) {
            mainState.wordItem?.let { wordItem ->
                // Display the word in large text
                Text(
                    text = wordItem.word,
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(20.dp))

                // Display the phonetic transcription of the word
                Text(
                    text = wordItem.phonetic,
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        // A separate section below the word info
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 110.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 32.dp,
                        topEnd = 32.dp
                    )
                )
                .background(MaterialTheme.colorScheme.secondaryContainer.copy(0.7f))
        ) {
            // Shows a loading indicator if data is still being fetched
            if (mainState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                // If data is loaded, display the word's meanings
                mainState.wordItem?.let { wordItem ->
                    WordResult(wordItem)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    val dummyWordItem = WordItem(
        word = "Example",
        phonetic = "/ˈɛɡzæmpəl/",
        meanings = listOf(
            com.example.dictionary_app.domain.model.Meaning(
                partOfSpeech = "Noun",
                definitions = Definition(
                    definition = "A representative form or pattern",
                    example = "This painting is a prime example of his work."
                )
            )
        )
    )

    val dummyMainState = MainState(
        searchWord = "Example",
        wordItem = dummyWordItem,
        isLoading = false
    )

    MainScreen(mainState = dummyMainState, paddingValues = PaddingValues())
}