package com.roman.moviemania.app.navigation.routes

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.roman.moviemania.R
import com.roman.moviemania.core.presentation.Observe
import com.roman.moviemania.core.presentation.ObserveAsEvents
import com.roman.moviemania.core.presentation.utils.asStringRes
import com.roman.moviemania.genre.presentation.AdaptiveGenreGridDetailPaneView
import com.roman.moviemania.genre.presentation.GenreEvents
import com.roman.moviemania.genre.presentation.GenreViewModel
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.genreComposable(
    navigationBar: @Composable () -> Unit,
    onNavigationChange: (Route) -> Unit
) {
    composable<Route.Genre> {
        val context = LocalContext.current
        val viewModel = koinViewModel<GenreViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        LocalLifecycleOwner.current.lifecycle.Observe(viewModel::onLifecycleEvent)

        LaunchedEffect(Unit) {
            onNavigationChange(it.toRoute<Route.Genre>())
        }

        ObserveAsEvents(viewModel.events) { event ->
            when (event) {
                is GenreEvents.Error -> {
                    Toast.makeText(
                        context,
                        event.error.asStringRes(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                GenreEvents.About -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.about_message),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        AdaptiveGenreGridDetailPaneView(
            uiState = uiState,
            onAction = viewModel::onAction,
            navigationBar = navigationBar
        )
    }
}