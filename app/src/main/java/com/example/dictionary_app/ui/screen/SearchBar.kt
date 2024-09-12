package com.example.dictionary_app.ui.screen

import androidx.compose.ui.draw.alpha
import com.example.dictionary_app.presentation.MainState
import com.example.dictionary_app.presentation.MainUiEvents
import com.example.dictionary_app.presentation.MainViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dictionary_app.R

// Search bar composable that handles the user input for searching words
@Composable
fun SearchBar(mainState: MainState, mainViewModel: MainViewModel) {
    OutlinedTextField(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 32.dp)
            .fillMaxWidth(),
        // Rounded corners for the search bar
        shape = MaterialTheme.shapes.medium,
        value = mainState.searchWord,
        // Updates the search word in the ViewModel when the user types
        onValueChange = {
            mainViewModel.onEvent(MainUiEvents.OnSearchWordChange(it))
        },
        // Search icon that triggers the search when clicked
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search_a_word),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clickable {
                        mainViewModel.onEvent(MainUiEvents.OnSearchWordClick)
                    }
            )
        },
        // Placeholder label for the search bar
        label = {
            Text(
                text = stringResource(R.string.search_a_word),
                fontSize = 15.sp,
                modifier = Modifier.alpha(0.7f)
            )
        },
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 19.sp,
        )
    )
}