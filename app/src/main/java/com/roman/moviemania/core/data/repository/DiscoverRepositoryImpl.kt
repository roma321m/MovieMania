package com.roman.moviemania.core.data.repository

import android.util.Log
import com.roman.moviemania.core.data.mappers.toMovie
import com.roman.moviemania.core.data.network.RemoteDiscoverDataSource
import com.roman.moviemania.core.domain.model.DiscoverSortOptions
import com.roman.moviemania.core.domain.model.ImageConfiguration
import com.roman.moviemania.core.domain.model.Movie
import com.roman.moviemania.core.domain.repository.DiscoverRepository
import com.roman.moviemania.core.domain.utils.DataError
import com.roman.moviemania.core.domain.utils.Result
import com.roman.moviemania.core.domain.utils.map


class DiscoverRepositoryImpl(
    private val remoteDiscoverDataSource: RemoteDiscoverDataSource
) : DiscoverRepository {

    companion object {
        private const val TAG = "DiscoverRepository"
    }

    override suspend fun getDiscoverMovieByGenre(
        imageConfiguration: ImageConfiguration?,
        page: Int,
        genreId: Int,
        sort: DiscoverSortOptions
    ): Result<List<Movie>, DataError.Network> {
        Log.d(TAG, "getDiscoverMovieByGenre: page:$page, genreId:$genreId")

        return remoteDiscoverDataSource
            .getMoviesByGenre(page, genreId, sort)
            .map { discoverResponseDto ->
                discoverResponseDto.results?.map { movieDto ->
                    movieDto.toMovie(imageConfiguration)
                } ?: emptyList()
            }
    }

    override suspend fun getNowPlaying(
        imageConfiguration: ImageConfiguration?
    ): Result<List<Movie>, DataError.Network> {
        Log.d(TAG, "getNowPlaying")

        return remoteDiscoverDataSource
            .getNowPlaying()
            .map { discoverResponseDto ->
                discoverResponseDto.results?.map { movieDto ->
                    movieDto.toMovie(imageConfiguration)
                } ?: emptyList()
            }
    }

    override suspend fun getPopular(
        imageConfiguration: ImageConfiguration?
    ): Result<List<Movie>, DataError.Network> {
        Log.d(TAG, "getPopular")

        return remoteDiscoverDataSource
            .getPopular()
            .map { discoverResponseDto ->
                discoverResponseDto.results?.map { movieDto ->
                    movieDto.toMovie(imageConfiguration)
                } ?: emptyList()
            }
    }

    override suspend fun getTopRated(
        imageConfiguration: ImageConfiguration?
    ): Result<List<Movie>, DataError.Network> {
        Log.d(TAG, "getTopRated")

        return remoteDiscoverDataSource
            .getTopRated()
            .map { discoverResponseDto ->
                discoverResponseDto.results?.map { movieDto ->
                    movieDto.toMovie(imageConfiguration)
                } ?: emptyList()
            }
    }

    override suspend fun getUpcoming(
        imageConfiguration: ImageConfiguration?
    ): Result<List<Movie>, DataError.Network> {
        Log.d(TAG, "getUpcoming")

        return remoteDiscoverDataSource
            .getUpcoming()
            .map { discoverResponseDto ->
                discoverResponseDto.results?.map { movieDto ->
                    movieDto.toMovie(imageConfiguration)
                } ?: emptyList()
            }
    }

}