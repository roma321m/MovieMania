package com.roman.moviemania.core.data.network

import com.roman.moviemania.core.data.dto.DiscoverResponseDto
import com.roman.moviemania.core.domain.model.DiscoverSortOptions
import com.roman.moviemania.core.domain.utils.DataError
import com.roman.moviemania.core.domain.utils.Result

interface RemoteDiscoverDataSource {
    suspend fun getMoviesByGenre(
        page: Int,
        genreId: Int,
        sort: DiscoverSortOptions
    ): Result<DiscoverResponseDto, DataError.Network>
}