package com.roman.moviemania.core.data.network

import com.roman.moviemania.core.data.dto.ConfigurationResponseDto
import com.roman.moviemania.core.domain.utils.DataError
import com.roman.moviemania.core.domain.utils.Result

interface RemoteConfigurationDataSource {
    suspend fun getConfigDetails(): Result<ConfigurationResponseDto, DataError.Network>
}