package com.roman.moviemania.genre.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.roman.moviemania.genre.presentation.details.GenreDetailsScreen
import com.roman.moviemania.genre.presentation.grid.GenreGridScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AdaptiveGenreGridDetailPaneView(
    uiState: GenreUiState,
    onAction: (GenreAction) -> Unit,
    navigationBar: @Composable () -> Unit,
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    val scope = rememberCoroutineScope()

    LaunchedEffect(navigator.scaffoldValue.primary, navigator.scaffoldValue.secondary) {
        if (navigator.scaffoldValue.secondary == PaneAdaptedValue.Hidden ||
            navigator.scaffoldValue.secondary == PaneAdaptedValue.Expanded &&
            navigator.scaffoldValue.primary == PaneAdaptedValue.Expanded
        ) {
            onAction(GenreAction.OnBarStatusChange(false))
        } else {
            onAction(GenreAction.OnBarStatusChange(true))
        }
    }

    NavigableListDetailPaneScaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        navigator = navigator,
        listPane = {
            AnimatedPane {
                GenreGridScreen(
                    uiState = uiState,
                    onAction = { action ->
                        onAction(action)
                        when (action) {
                            is GenreAction.OnMovieSelected -> {
                                scope.launch {
                                    navigator.navigateTo(
                                        pane = ListDetailPaneScaffoldRole.Detail
                                    )
                                }
                            }

                            else -> Unit
                        }
                    },
                    navigationBar = navigationBar
                )
            }
        },
        detailPane = {
            AnimatedPane {
                GenreDetailsScreen(
                    uiState = uiState
                )
            }
        }
    )
}