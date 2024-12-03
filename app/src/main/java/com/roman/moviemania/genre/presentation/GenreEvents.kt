package com.roman.moviemania.genre.presentation

import com.roman.moviemania.core.domain.utils.DataError

sealed interface GenreEvents {
    data class Error(val error: DataError.Network) : GenreEvents
    data object About : GenreEvents
}