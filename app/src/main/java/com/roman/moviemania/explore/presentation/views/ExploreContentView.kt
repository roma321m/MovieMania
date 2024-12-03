package com.roman.moviemania.explore.presentation.views

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.roman.moviemania.R
import com.roman.moviemania.explore.presentation.ExploreUiState

@Composable
fun ExploreContentView(
    uiState: ExploreUiState,
    scrollState: ScrollState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        ExploreRowView(
            title = R.string.explore_now_playing_title,
            list = uiState.nowPlayingMovies,
        )
        Spacer(Modifier.height(4.dp))
        ExploreRowView(
            title = R.string.explore_top_rated_title,
            list = uiState.topRated,
        )
        Spacer(Modifier.height(4.dp))
        ExploreRowView(
            title = R.string.explore_popular_title,
            list = uiState.popular,
        )
        Spacer(Modifier.height(4.dp))
        ExploreRowView(
            title = R.string.explore_upcoming_title,
            list = uiState.upcoming,
        )
    }
}