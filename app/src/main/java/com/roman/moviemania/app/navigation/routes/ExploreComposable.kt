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
import com.roman.moviemania.core.presentation.utils.asUiText
import com.roman.moviemania.explore.presentation.ExploreEvents
import com.roman.moviemania.explore.presentation.ExploreScreen
import com.roman.moviemania.explore.presentation.ExploreViewModel
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.exploreComposable(
    navigationBar: @Composable () -> Unit,
    onNavigationChange: (Route) -> Unit
) {
    composable<Route.Explore> {
        val context = LocalContext.current
        val viewModel = koinViewModel<ExploreViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        LocalLifecycleOwner.current.lifecycle.Observe(viewModel::onLifecycleEvent)

        LaunchedEffect(Unit) {
            onNavigationChange(it.toRoute<Route.Explore>())
        }

        ObserveAsEvents(viewModel.events) { event ->
            when (event) {
                is ExploreEvents.Error -> {
                    Toast.makeText(
                        context,
                        event.error.asUiText().asString(context),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                ExploreEvents.About -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.about_message),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        ExploreScreen(
            uiState = uiState,
            onAction = viewModel::onAction,
            navigationBar = navigationBar
        )
    }
}