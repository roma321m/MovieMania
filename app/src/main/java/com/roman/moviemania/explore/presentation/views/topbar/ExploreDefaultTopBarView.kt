@file:OptIn(ExperimentalMaterial3Api::class)

package com.roman.moviemania.explore.presentation.views.topbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.roman.moviemania.R
import com.roman.moviemania.explore.presentation.ExploreAction
import com.roman.moviemania.explore.presentation.ExploreUiState

@Composable
fun ExploreDefaultTopBarView(
    scrollBehavior: TopAppBarScrollBehavior,
    uiState: ExploreUiState,
    onAction: (ExploreAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.explore_top_bar_title)
            )
        },
        actions = {
            ExploreTopBarMoreActionView(
                uiState = uiState,
                onAction = onAction
            )
        }
    )
}