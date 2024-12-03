package com.roman.moviemania.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.roman.moviemania.app.navigation.routes.exploreComposable
import com.roman.moviemania.app.navigation.routes.genreComposable
import com.roman.moviemania.core.presentation.ObserveAsEvents


@Composable
fun AppNavigation(
    navigator: Navigator,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    ObserveAsEvents(flow = navigator.navigationActions) { action ->
        when (action) {
            is NavigationAction.NavigateUp -> navController.navigateUp()
            is NavigationAction.Navigate -> navController.navigate(action.destination) {
                action.navOptions(this)
            }
        }
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = navigator.startDestination,
    ) {
        exploreComposable()
        genreComposable()
    }

}