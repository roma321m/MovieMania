package com.roman.moviemania.core.domain.repository

import com.roman.moviemania.core.domain.model.ImageConfiguration
import com.roman.moviemania.core.domain.model.Movie
import com.roman.moviemania.core.domain.utils.DataError
import com.roman.moviemania.core.domain.utils.Result

interface DiscoverRepository {
    suspend fun getDiscoverMovieByGenre(
        imageConfiguration: ImageConfiguration?,
        page: Int,
        genreId: Int
    ): Result<List<Movie>, DataError.Network>
}