package com.roman.moviemania.explore.data.dto

import com.roman.moviemania.core.data.dto.MovieDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseDto(
    val page: Int,
    val results: List<MovieDto>? = null,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int,
)
