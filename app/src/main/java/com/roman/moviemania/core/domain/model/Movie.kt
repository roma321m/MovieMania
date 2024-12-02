package com.roman.moviemania.core.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val adult: Boolean,
    val originalLanguage: String,
    val popularity: Float,
    val voteCount: Int,
    val voteAverage: Float,
    val releaseDate: String,
    val posterPath: String,
    val backdropPath: String,
    val genreIds: List<Int>,
    val originalTitle: String,
    val video: Boolean,
)
