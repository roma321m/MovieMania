package com.roman.moviemania.core.data.network

import com.roman.moviemania.core.data.dto.ConfigurationResponseDto
import com.roman.moviemania.core.data.network.utils.constructUrl
import com.roman.moviemania.core.data.network.utils.safeCall
import com.roman.moviemania.core.domain.utils.DataError
import com.roman.moviemania.core.domain.utils.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteConfigurationDataSourceImpl(
    private val httpClient: HttpClient,
) : RemoteConfigurationDataSource {

    override suspend fun getConfigDetails(): Result<ConfigurationResponseDto, DataError.Network> {
        return safeCall<ConfigurationResponseDto> {
            httpClient.get(
                urlString = constructUrl("/3/configuration")
            )
        }
    }

}