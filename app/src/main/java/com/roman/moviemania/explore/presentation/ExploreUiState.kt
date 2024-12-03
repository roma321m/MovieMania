package com.roman.moviemania.explore.presentation

data class ExploreUiState(
    val isLoading: Boolean = false,
    val showMoreMenu: Boolean = false,
    val searchActive: Boolean = false,
    val searchQuery: String = "",
)