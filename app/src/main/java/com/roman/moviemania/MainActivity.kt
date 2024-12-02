package com.roman.moviemania

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.roman.moviemania.app.navigation.NavigationBarDefaults
import com.roman.moviemania.app.ui.theme.MovieManiaTheme
import com.roman.moviemania.core.presentation.Observe
import com.roman.moviemania.core.presentation.ObserveAsEvents
import com.roman.moviemania.core.presentation.components.bottombar.BottomBarView
import com.roman.moviemania.genre.presentation.GenreEvents
import com.roman.moviemania.genre.presentation.GenreViewModel
import com.roman.moviemania.genre.presentation.grid.GenreGridScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MovieManiaTheme {
                // Fixme - temp for UI implementation testing
                val viewModel = remember {
                    GenreViewModel()
                }
                val state by viewModel.uiState.collectAsStateWithLifecycle()
                LocalLifecycleOwner.current.lifecycle.Observe(viewModel::onLifecycleEvent)

                ObserveAsEvents(viewModel.events) { event ->
                    when (event) {
                        is GenreEvents.Error -> {
                            // todo - show error in toast or snackbar
                        }
                    }
                }

                GenreGridScreen(
                    uiState = state,
                    onAction = viewModel::onAction,
                    navigationBar = {
                        BottomBarView(
                            selectedItemIndex = 1,
                            onNavItemClick = {},
                            items = NavigationBarDefaults.items
                        )
                    }
                )
            }
        }
    }

}