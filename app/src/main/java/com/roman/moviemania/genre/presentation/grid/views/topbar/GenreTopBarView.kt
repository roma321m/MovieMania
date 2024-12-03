@file:OptIn(ExperimentalMaterial3Api::class)

package com.roman.moviemania.genre.presentation.grid.views.topbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.roman.moviemania.R
import com.roman.moviemania.genre.presentation.GenreAction
import com.roman.moviemania.genre.presentation.GenreUiState

@Composable
fun GenreTopBarView(
    scrollBehavior: TopAppBarScrollBehavior,
    uiState: GenreUiState,
    onAction: (GenreAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.genre_top_bar_title)
            )
        },
        actions = {
            GenreTopBarSortActionView(
                uiState = uiState,
                onAction = onAction
            )
            GenreTopBarMoreActionView(
                uiState = uiState,
                onAction = onAction
            )
        }
    )
}