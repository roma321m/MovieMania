package com.roman.moviemania.explore.domain.repository

import com.roman.moviemania.core.domain.model.ImageConfiguration
import com.roman.moviemania.core.domain.model.Movie
import com.roman.moviemania.core.domain.utils.DataError
import com.roman.moviemania.core.domain.utils.Result

interface SearchRepository {

    suspend fun getMoviesWithQuery(
        imageConfiguration: ImageConfiguration?,
        query: String
    ): Result<List<Movie>, DataError.Network>

}