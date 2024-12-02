package com.roman.moviemania.genre.data.network


import com.roman.moviemania.core.domain.utils.DataError
import com.roman.moviemania.core.domain.utils.Result
import com.roman.moviemania.genre.data.dto.MovieListResponseDto

interface RemoteGenreDataSource {

    suspend fun getGenres(
        language: String,
    ): Result<MovieListResponseDto, DataError.Network>

}