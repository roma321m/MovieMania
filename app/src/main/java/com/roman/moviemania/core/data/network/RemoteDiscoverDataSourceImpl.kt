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

    override suspend fun getNowPlaying(): Result<DiscoverResponseDto, DataError.Network> {
        Log.d(TAG, "getNowPlaying")

        return safeCall<DiscoverResponseDto> {
            httpClient.get(
                urlString = constructUrl("/3/movie/now_playing")
            )
        }
    }

    override suspend fun getPopular(): Result<DiscoverResponseDto, DataError.Network> {
        Log.d(TAG, "getPopular")

        return safeCall<DiscoverResponseDto> {
            httpClient.get(
                urlString = constructUrl("/3/movie/popular")
            )
        }
    }

    override suspend fun getTopRated(): Result<DiscoverResponseDto, DataError.Network> {
        Log.d(TAG, "getTopRated")

        return safeCall<DiscoverResponseDto> {
            httpClient.get(
                urlString = constructUrl("/3/movie/top_rated")
            )
        }
    }

    override suspend fun getUpcoming(): Result<DiscoverResponseDto, DataError.Network> {
        Log.d(TAG, "getUpcoming")

        return safeCall<DiscoverResponseDto> {
            httpClient.get(
                urlString = constructUrl("/3/movie/upcoming")
            )
        }
    }

}