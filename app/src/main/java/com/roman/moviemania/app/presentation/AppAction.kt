package com.roman.moviemania.app.presentation

sealed interface AppAction {
    data class OnBottomNavItemClick(val index: Int) : AppAction
}