package com.roman.moviemania.genre.presentation.grid.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.roman.moviemania.genre.presentation.GenreAction
import com.roman.moviemania.genre.presentation.GenreUiState

@Composable
fun GenreGridContent(
    uiState: GenreUiState,
    onAction: (GenreAction) -> Unit,
    lazyGridState: LazyGridState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GenreGridTabRowView(
            genres = uiState.genres,
            selectedGenre = uiState.selectedGenre,
            onGenreSelected = { genre ->
                onAction(GenreAction.OnGenreSelected(genre))
            }
        )
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            state = lazyGridState,
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Log.d("testing", "GenreGridContent: ${uiState.movies.size}")
            items(
                items = uiState.movies,
                key = {
                    it.id
                }
            ) { movie ->
                GenreMovieCardView(
                    movie = movie
                )
            }
        }
    }
}