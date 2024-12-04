package com.roman.moviemania.explore.data.network

import android.util.Log
import com.roman.moviemania.core.data.network.utils.constructUrl
import com.roman.moviemania.core.data.network.utils.safeCall
import com.roman.moviemania.core.domain.utils.DataError
import com.roman.moviemania.core.domain.utils.Result
import com.roman.moviemania.explore.data.dto.SearchResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class RemoteSearchDataSourceImpl(
    private val httpClient: HttpClient,
) : RemoteSearchDataSource {

    companion object {
        private const val TAG = "RemoteSearchDataSourceImpl"
    }

    override suspend fun getMoviesWithQuery(
        query: String
    ): Result<SearchResponseDto, DataError.Network> {
        Log.d(TAG, "getMoviesWithQuery: $query")

        return safeCall<SearchResponseDto> {
            httpClient.get(
                urlString = constructUrl("/3/search/movie")
            ) {
                parameter("query", query)
            }
        }
    }

}