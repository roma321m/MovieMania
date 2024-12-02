package com.roman.moviemania.core.data.dto

import kotlinx.serialization.SerialName

data class DiscoverResponseDto(
    val page: Int,
    val results: List<MovieDto>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int,
)
