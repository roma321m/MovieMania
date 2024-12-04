package com.roman.moviemania.app.presentation

import com.roman.moviemania.app.navigation.routes.Route

sealed interface AppAction {
    data class OnBottomNavItemClick(val index: Int) : AppAction
    data class OnNavigationChange(val route: Route) : AppAction
}