package com.roman.moviemania.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageConfigurationDto(
    @SerialName("base_url") val baseUrl: String,
    @SerialName("secure_base_url") val secureBaseUrl: String,
    @SerialName("backdrop_sizes") val backdropSizes: List<String>,
    @SerialName("logo_sizes") val logoSizes: List<String>,
    @SerialName("poster_sizes") val posterSizes: List<String>,
    @SerialName("profile_sizes") val profileSizes: List<String>,
    @SerialName("still_sizes") val stillSizes: List<String>,
)
