package com.roman.moviemania.core.data.repository

import android.util.Log
import com.roman.moviemania.core.data.mappers.toImageConfiguration
import com.roman.moviemania.core.data.network.RemoteConfigurationDataSource
import com.roman.moviemania.core.domain.model.ImageConfiguration
import com.roman.moviemania.core.domain.repository.ConfigurationRepository
import com.roman.moviemania.core.domain.utils.DataError
import com.roman.moviemania.core.domain.utils.Result
import com.roman.moviemania.core.domain.utils.map


class ConfigurationRepositoryImpl(
    private val remoteConfigurationDataSource: RemoteConfigurationDataSource
) : ConfigurationRepository {

    companion object {
        private const val TAG = "ConfigurationRepository"
    }

    override suspend fun getImageConfiguration(): Result<ImageConfiguration, DataError.Network> {
        Log.d(TAG, "getConfigurationDetails")

        return remoteConfigurationDataSource
            .getConfigDetails()
            .map { responseDto ->
                responseDto.images.toImageConfiguration()
            }
    }

}