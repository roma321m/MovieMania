package com.roman.moviemania.core.data.mappers

import com.roman.moviemania.core.data.dto.MovieDto
import com.roman.moviemania.core.domain.model.ImageConfiguration
import com.roman.moviemania.core.domain.model.Movie

fun MovieDto.toMovie(
    config: ImageConfiguration?
): Movie {
    val posterPath = config?.secureBaseUrl + config?.posterSizes?.last() + posterPath
    val backdropPath = config?.secureBaseUrl + config?.backdropSizes?.last() + backdropPath
    val releaseDate = releaseDate?.split("-")?.first() ?: ""

    return Movie(
        id = id,
        title = title ?: "",
        overview = overview ?: "",
        adult = adult,
        originalLanguage = originalLanguage ?: "",
        popularity = popularity,
        voteCount = voteCount,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        posterPath = posterPath,
        backdropPath = backdropPath,
        genreIds = genreIds ?: emptyList(),
        originalTitle = originalTitle ?: "",
        video = video,
    )
}