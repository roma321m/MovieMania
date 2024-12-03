package com.roman.moviemania.app.navigation.routes

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.roman.moviemania.core.presentation.Observe
import com.roman.moviemania.core.presentation.ObserveAsEvents
import com.roman.moviemania.core.presentation.utils.asUiText
import com.roman.moviemania.genre.presentation.GenreEvents
import com.roman.moviemania.genre.presentation.GenreViewModel
import com.roman.moviemania.genre.presentation.grid.GenreGridScreen
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.genreComposable(
    navigationBar: @Composable () -> Unit
) {
    composable<Route.Genre> {
        val context = LocalContext.current
        val viewModel = koinViewModel<GenreViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        LocalLifecycleOwner.current.lifecycle.Observe(viewModel::onLifecycleEvent)

        ObserveAsEvents(viewModel.events) { event ->
            when (event) {
                is GenreEvents.Error -> {
                    Toast.makeText(
                        context,
                        event.error.asUiText().asString(context),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        GenreGridScreen(
            uiState = uiState,
            onAction = viewModel::onAction,
            navigationBar = navigationBar
        )
    }
}