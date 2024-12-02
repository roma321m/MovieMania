package com.roman.moviemania.genre.domain

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
)
