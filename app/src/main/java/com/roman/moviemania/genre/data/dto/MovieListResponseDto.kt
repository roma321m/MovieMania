package com.roman.moviemania.genre.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MovieListResponseDto(
    val genres: List<GenreDto>? = null,
)