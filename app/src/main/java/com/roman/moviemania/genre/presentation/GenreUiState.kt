package com.roman.moviemania.genre.presentation

import com.roman.moviemania.core.domain.model.DiscoverSortOptions
import com.roman.moviemania.core.domain.model.Movie
import com.roman.moviemania.genre.domain.model.Genre

data class GenreUiState(
    val showMoreMenu: Boolean = false,
    val showSortMenu: Boolean = false,
    val showBars: Boolean = true,
    val isLoading: Boolean = true,
    val sortBy: DiscoverSortOptions = DiscoverSortOptions.POPULARITY,
    val genres: List<Genre> = emptyList(),
    val movies: List<Movie> = emptyList(),
    val selectedGenre: Genre? = null,
    val selectedMovie: Movie? = null,
)