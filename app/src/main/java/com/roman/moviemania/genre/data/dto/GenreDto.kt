package com.roman.moviemania.genre.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    val id: Int,
    val name: String? = null
)