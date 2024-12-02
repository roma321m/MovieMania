package com.roman.moviemania.genre.presentation

import com.roman.moviemania.genre.domain.Genre
import com.roman.moviemania.genre.domain.Movie

sealed interface GenreAction {
    data object OnSearchFabClick : GenreAction
    data class OnGenreSelected(val genre: Genre) : GenreAction
    data class OnMovieSelected(val movie: Movie) : GenreAction
}