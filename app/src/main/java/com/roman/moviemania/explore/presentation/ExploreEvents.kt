package com.roman.moviemania.explore.presentation

import com.roman.moviemania.core.domain.utils.DataError

sealed interface ExploreEvents {
    data class Error(val error: DataError.Network) : ExploreEvents
}