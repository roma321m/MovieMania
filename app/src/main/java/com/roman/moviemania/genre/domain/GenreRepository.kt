package com.roman.moviemania.genre.domain

import com.roman.moviemania.core.domain.utils.DataError
import com.roman.moviemania.core.domain.utils.Result

interface GenreRepository {
    suspend fun getGenres(language: String = "en"): Result<List<Genre>, DataError.Network>
}