package com.roman.moviemania.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigurationResponseDto(
    val images: ImageConfigurationDto,
    @SerialName("change_keys") val changeKeys: List<String>
)