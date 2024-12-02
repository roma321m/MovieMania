package com.roman.moviemania.genre.data.network

import com.roman.moviemania.core.data.network.utils.constructUrl
import com.roman.moviemania.core.data.network.utils.safeCall
import com.roman.moviemania.core.domain.utils.DataError
import com.roman.moviemania.core.domain.utils.Result
import com.roman.moviemania.genre.data.dto.MovieListResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class RemoteGenreDataSourceImpl(
    private val httpClient: HttpClient,
) : RemoteGenreDataSource {

    override suspend fun getGenres(
        language: String
    ): Result<MovieListResponseDto, DataError.Network> {
        return safeCall<MovieListResponseDto> {
            httpClient.get(
                urlString = constructUrl("/3/genre/movie/list")
            ) {
                parameter("language", language)
            }
        }
    }

}