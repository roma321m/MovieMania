package com.roman.moviemania.genre.data.repository

import android.util.Log
import com.roman.moviemania.core.domain.utils.DataError
import com.roman.moviemania.core.domain.utils.Result
import com.roman.moviemania.core.domain.utils.map
import com.roman.moviemania.genre.data.mappers.toGenre
import com.roman.moviemania.genre.data.network.RemoteGenreDataSource
import com.roman.moviemania.genre.domain.model.Genre
import com.roman.moviemania.genre.domain.repository.GenreRepository

class GenreRepositoryImpl(
    private val remoteGenreDataSource: RemoteGenreDataSource
) : GenreRepository {

    companion object {
        private const val TAG = "GenreRepository"
    }

    override suspend fun getGenres(
        language: String
    ): Result<List<Genre>, DataError.Network> {
        Log.d(TAG, "getGenres")

        return remoteGenreDataSource
            .getGenres(language)
            .map { responseDto ->
                responseDto.genres?.map { dto ->
                    dto.toGenre()
                } ?: emptyList()
            }
    }

}