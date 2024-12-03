package com.roman.moviemania.core.domain.repository

import com.roman.moviemania.core.domain.model.DiscoverSortOptions
import com.roman.moviemania.core.domain.model.ImageConfiguration
import com.roman.moviemania.core.domain.model.Movie
import com.roman.moviemania.core.domain.utils.DataError
import com.roman.moviemania.core.domain.utils.Result

interface DiscoverRepository {

    suspend fun getDiscoverMovieByGenre(
        imageConfiguration: ImageConfiguration?,
        page: Int,
        genreId: Int,
        sort: DiscoverSortOptions = DiscoverSortOptions.REVENUE
    ): Result<List<Movie>, DataError.Network>

    suspend fun getNowPlaying(
        imageConfiguration: ImageConfiguration?,
    ): Result<List<Movie>, DataError.Network>

    suspend fun getPopular(
        imageConfiguration: ImageConfiguration?,
    ): Result<List<Movie>, DataError.Network>

    suspend fun getTopRated(
        imageConfiguration: ImageConfiguration?,
    ): Result<List<Movie>, DataError.Network>

    suspend fun getUpcoming(
        imageConfiguration: ImageConfiguration?,
    ): Result<List<Movie>, DataError.Network>

}