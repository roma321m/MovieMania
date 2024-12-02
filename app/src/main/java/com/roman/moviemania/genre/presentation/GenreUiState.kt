package com.roman.moviemania.genre.presentation

import com.roman.moviemania.core.domain.model.Movie
import com.roman.moviemania.genre.domain.model.Genre

data class GenreUiState(
    val isLoading: Boolean = true,
    val genres: List<Genre> = emptyList(),
    val movies: List<Movie> = emptyList(),
    val selectedGenre: Genre? = null,
    val selectedMovie: Movie? = null,
)