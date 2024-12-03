package com.roman.moviemania.explore.presentation

sealed interface ExploreAction {
    data object OnSearchFabClick : ExploreAction
}