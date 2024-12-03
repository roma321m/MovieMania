package com.roman.moviemania.core.domain.repository

import com.roman.moviemania.core.domain.model.ImageConfiguration
import com.roman.moviemania.core.domain.utils.DataError
import com.roman.moviemania.core.domain.utils.Result

interface ConfigurationRepository {
    var cachedImageConfiguration: ImageConfiguration?
    suspend fun getImageConfiguration(): Result<ImageConfiguration, DataError.Network>
}