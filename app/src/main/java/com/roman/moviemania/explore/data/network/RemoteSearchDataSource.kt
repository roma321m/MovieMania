package com.roman.moviemania.explore.data.network

import com.roman.moviemania.core.domain.utils.DataError
import com.roman.moviemania.core.domain.utils.Result
import com.roman.moviemania.explore.data.dto.SearchResponseDto

interface RemoteSearchDataSource {

    suspend fun getMoviesWithQuery(
        query: String
    ): Result<SearchResponseDto, DataError.Network>

}