package com.roman.moviemania.genre.domain.repository

import com.roman.moviemania.core.domain.utils.DataError
import com.roman.moviemania.core.domain.utils.Result
import com.roman.moviemania.genre.domain.model.Genre

interface GenreRepository {
    suspend fun getGenres(language: String = "en"): Result<List<Genre>, DataError.Network>
}