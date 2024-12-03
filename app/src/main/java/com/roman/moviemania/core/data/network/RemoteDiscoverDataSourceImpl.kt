package com.roman.moviemania.core.data.network

import android.util.Log
import com.roman.moviemania.core.data.dto.DiscoverResponseDto
import com.roman.moviemania.core.data.mappers.toDtoString
import com.roman.moviemania.core.data.network.utils.constructUrl
import com.roman.moviemania.core.data.network.utils.safeCall
import com.roman.moviemania.core.domain.model.DiscoverSortOptions
import com.roman.moviemania.core.domain.utils.DataError
import com.roman.moviemania.core.domain.utils.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class RemoteDiscoverDataSourceImpl(
    private val httpClient: HttpClient,
) : RemoteDiscoverDataSource {

    companion object {
        private const val TAG = "RemoteDiscoverDataSource"
    }

    override suspend fun getMoviesByGenre(
        page: Int,
        genreId: Int,
        sort: DiscoverSortOptions
    ): Result<DiscoverResponseDto, DataError.Network> {
        Log.d(TAG, "getMoviesByGenre")

        return safeCall<DiscoverResponseDto> {
            httpClient.get(
                urlString = constructUrl("/3/discover/movie")
            ) {
                parameter("sort_by", sort.toDtoString())
                parameter("page", page)
                parameter("with_genres", genreId)
            }
        }
    }

}