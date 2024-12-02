package com.roman.moviemania.core.data.mappers

import com.roman.moviemania.core.data.dto.MovieDto
import com.roman.moviemania.core.domain.model.Movie

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        adult = adult,
        originalLanguage = originalLanguage,
        popularity = popularity,
        voteCount = voteCount,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        posterPath = posterPath,
        backdropPath = backdropPath,
        genreIds = genreIds,
        originalTitle = originalTitle,
        video = video,
    )
}