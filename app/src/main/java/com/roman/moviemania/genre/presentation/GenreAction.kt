package com.roman.moviemania.genre.presentation

import com.roman.moviemania.core.domain.model.Movie
import com.roman.moviemania.genre.domain.model.Genre

sealed interface GenreAction {
    data object OnSearchFabClick : GenreAction
    data class OnGenreSelected(val genre: Genre) : GenreAction
    data class OnMovieSelected(val movie: Movie) : GenreAction
}