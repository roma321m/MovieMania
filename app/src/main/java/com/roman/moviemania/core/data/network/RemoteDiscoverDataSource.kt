package com.roman.moviemania.core.data.network

import com.roman.moviemania.core.data.dto.DiscoverResponseDto
import com.roman.moviemania.core.domain.utils.DataError
import com.roman.moviemania.core.domain.utils.Result

interface RemoteDiscoverDataSource {
    suspend fun getMoviesByGenre(
        page: Int,
        genreId: Int
    ): Result<DiscoverResponseDto, DataError.Network>
}