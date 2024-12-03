@file:OptIn(ExperimentalMaterial3Api::class)

package com.roman.moviemania.explore.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.roman.moviemania.app.ui.previews.ExploreUiStatePreviewParameterProvider
import com.roman.moviemania.app.ui.theme.MovieManiaTheme
import com.roman.moviemania.core.presentation.components.LoadingView
import com.roman.moviemania.explore.presentation.views.ExploreContentView
import com.roman.moviemania.explore.presentation.views.ExploreFabView
import com.roman.moviemania.explore.presentation.views.topbar.ExploreTopBarView

@Composable
fun ExploreScreen(
    uiState: ExploreUiState,
    onAction: (ExploreAction) -> Unit,
    navigationBar: @Composable () -> Unit = {},
) {
    val topBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ExploreTopBarView(
                uiState = uiState,
                onAction = onAction,
                scrollBehavior = topBarScrollBehavior
            )
        },
        floatingActionButton = {
            AnimatedVisibility(!uiState.searchActive) {
                ExploreFabView(
                    onFabClick = {
                        onAction(ExploreAction.OnSearchFabClick)
                    },
                    scrollState = scrollState
                )
            }
        },
        bottomBar = {
            navigationBar()
        }
    ) { innerPadding ->
        if (uiState.isLoading) {
            LoadingView(
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            ExploreContentView(
                modifier = Modifier.padding(innerPadding),
                scrollState = scrollState,
                uiState = uiState,
            )
        }
    }
}

@PreviewLightDark
@Composable
fun ExploreScreenPreview(
    @PreviewParameter(ExploreUiStatePreviewParameterProvider::class) uiState: ExploreUiState
) {
    MovieManiaTheme {
        ExploreScreen(
            uiState = uiState,
            onAction = {}
        )
    }
}