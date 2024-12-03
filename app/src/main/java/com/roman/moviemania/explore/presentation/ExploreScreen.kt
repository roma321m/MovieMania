@file:OptIn(ExperimentalMaterial3Api::class)

package com.roman.moviemania.explore.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.roman.moviemania.explore.presentation.views.ExploreFabView
import com.roman.moviemania.explore.presentation.views.topbar.ExploreTopBarView

@Composable
fun ExploreScreen(
    uiState: ExploreUiState,
    onAction: (ExploreAction) -> Unit,
    navigationBar: @Composable () -> Unit = {},
) {
    val topBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val scrollState = rememberLazyGridState()

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
        // todo
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Explore Screen"
            )
        }
    }
}