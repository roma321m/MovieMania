package com.roman.moviemania.genre.presentation

import com.roman.moviemania.genre.domain.Genre
import com.roman.moviemania.genre.domain.Movie

data class GenreUiState(
    val isLoading: Boolean = true,
    val genres: List<Genre> = emptyList(),
    val movies: List<Movie> = emptyList(),
    val selectedGenre: Genre? = null,
    val selectedMovie: Movie? = null,
)