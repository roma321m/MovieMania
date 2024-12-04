package com.roman.moviemania.explore.data.repository

import android.util.Log
import com.roman.moviemania.core.data.mappers.toMovie
import com.roman.moviemania.core.domain.model.ImageConfiguration
import com.roman.moviemania.core.domain.model.Movie
import com.roman.moviemania.core.domain.utils.DataError
import com.roman.moviemania.core.domain.utils.Result
import com.roman.moviemania.core.domain.utils.map
import com.roman.moviemania.explore.data.network.RemoteSearchDataSource
import com.roman.moviemania.explore.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val remoteSearchDataSource: RemoteSearchDataSource
) : SearchRepository {

    companion object {
        private const val TAG = "SearchRepositoryImpl"
    }

    override suspend fun getMoviesWithQuery(
        imageConfiguration: ImageConfiguration?,
        query: String
    ): Result<List<Movie>, DataError.Network> {
        Log.d(TAG, "getMoviesWithQuery: $query")

        return remoteSearchDataSource
            .getMoviesWithQuery(query)
            .map { searchResponseDto ->
                searchResponseDto.results?.map { movieDto ->
                    movieDto.toMovie(imageConfiguration)
                } ?: emptyList()
            }
    }

}