package com.roman.moviemania.genre.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.roman.moviemania.genre.presentation.GenreUiState
import com.roman.moviemania.genre.presentation.details.views.GenreDetailsContentView
import com.roman.moviemania.genre.presentation.details.views.GenreDetailsEmptyView

@Composable
fun GenreDetailsScreen(
    uiState: GenreUiState,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .systemBarsPadding()
    ) {
        if (uiState.selectedMovie == null) {
            GenreDetailsEmptyView()
        } else {
            GenreDetailsContentView(
                movie = uiState.selectedMovie,
                genres = uiState.genres
            )
        }
    }
}