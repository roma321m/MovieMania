package com.roman.moviemania.core.data.mappers

import com.roman.moviemania.core.data.dto.ImageConfigurationDto
import com.roman.moviemania.core.domain.model.ImageConfiguration

fun ImageConfigurationDto.toImageConfiguration(): ImageConfiguration {
    return ImageConfiguration(
        baseUrl = baseUrl,
        secureBaseUrl = secureBaseUrl,
        backdropSizes = backdropSizes,
        logoSizes = logoSizes,
        posterSizes = posterSizes,
        profileSizes = profileSizes,
        stillSizes = stillSizes
    )
}