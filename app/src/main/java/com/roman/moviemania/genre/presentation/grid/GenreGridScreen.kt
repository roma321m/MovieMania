@file:OptIn(ExperimentalMaterial3Api::class)

package com.roman.moviemania.genre.presentation.grid

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.roman.moviemania.app.ui.previews.GenreUiStatePreviewParameterProvider
import com.roman.moviemania.app.ui.theme.MovieManiaTheme
import com.roman.moviemania.core.presentation.components.LoadingView
import com.roman.moviemania.genre.presentation.GenreAction
import com.roman.moviemania.genre.presentation.GenreUiState
import com.roman.moviemania.genre.presentation.grid.views.GenreGridContent
import com.roman.moviemania.genre.presentation.grid.views.topbar.GenreTopBarView

@Composable
fun GenreGridScreen(
    uiState: GenreUiState,
    onAction: (GenreAction) -> Unit,
    navigationBar: @Composable () -> Unit = {},
) {
    val topBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val lazyGridState = rememberLazyGridState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(topBarScrollBehavior.nestedScrollConnection),
        topBar = {
            GenreTopBarView(
                scrollBehavior = topBarScrollBehavior,
                uiState = uiState,
                onAction = onAction
            )
        },
        bottomBar = {
            navigationBar()
        }
    ) { innerPadding ->
        if (uiState.isLoading) {
            LoadingView(modifier = Modifier.padding(innerPadding))
        } else {
            GenreGridContent(
                uiState = uiState,
                onAction = onAction,
                lazyGridState = lazyGridState,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@PreviewLightDark
@Composable
fun GenreGridScreenPreview(
    @PreviewParameter(GenreUiStatePreviewParameterProvider::class) uiState: GenreUiState,
) {
    MovieManiaTheme {
        Surface {
            GenreGridScreen(
                uiState = uiState,
                onAction = {}
            )
        }
    }
}