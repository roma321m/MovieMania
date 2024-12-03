package com.roman.moviemania.genre.data.mappers

import com.roman.moviemania.genre.data.dto.GenreDto
import com.roman.moviemania.genre.domain.model.Genre

fun GenreDto.toGenre(): Genre {
    return Genre(
        id = id,
        name = name ?: ""
    )
}