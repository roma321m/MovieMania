package com.roman.moviemania.explore.presentation

import com.roman.moviemania.core.domain.model.Movie

data class ExploreUiState(
    val isLoading: Boolean = true,
    val showMoreMenu: Boolean = false,
    val searchActive: Boolean = false,
    val expendedSearch: Boolean = false,
    val searchQuery: String = "",
    val nowPlayingMovies: List<Movie> = emptyList(),
    val popular: List<Movie> = emptyList(),
    val topRated: List<Movie> = emptyList(),
    val upcoming: List<Movie> = emptyList(),
    val searchResults: List<Movie> = emptyList(),
)