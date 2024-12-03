package com.roman.moviemania.app.navigation

import androidx.navigation.NavOptionsBuilder
import com.roman.moviemania.app.navigation.routes.Route
import kotlinx.coroutines.flow.Flow

interface Navigator {

    val startDestination: Route
    val navigationActions: Flow<NavigationAction>

    suspend fun navigate(
        destination: Route,
        navOptions: NavOptionsBuilder.() -> Unit = {}
    )

    suspend fun navigateUp()

}