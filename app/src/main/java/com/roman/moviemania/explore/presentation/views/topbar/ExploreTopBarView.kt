@file:OptIn(ExperimentalMaterial3Api::class)

package com.roman.moviemania.explore.presentation.views.topbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.roman.moviemania.explore.presentation.ExploreAction
import com.roman.moviemania.explore.presentation.ExploreUiState

@Composable
fun ExploreTopBarView(
    scrollBehavior: TopAppBarScrollBehavior,
    uiState: ExploreUiState,
    onAction: (ExploreAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (uiState.searchActive) {
        ExploreSearchTopBarView(
            onAction = onAction,
            modifier = modifier,
            uiState = uiState
        )
    } else {
        ExploreDefaultTopBarView(
            scrollBehavior = scrollBehavior,
            onAction = onAction,
            modifier = modifier,
            uiState = uiState
        )
    }
}