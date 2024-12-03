package com.roman.moviemania.app.navigation

import androidx.navigation.NavOptionsBuilder
import com.roman.moviemania.app.navigation.routes.Route

sealed interface NavigationAction {

    data class Navigate(
        val destination: Route,
        val navOptions: NavOptionsBuilder.() -> Unit = {}
    ) : NavigationAction

    data object NavigateUp : NavigationAction

}