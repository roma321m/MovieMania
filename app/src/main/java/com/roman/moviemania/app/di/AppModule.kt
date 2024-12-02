package com.roman.moviemania.app.di

import com.roman.moviemania.core.data.network.RemoteConfigurationDataSource
import com.roman.moviemania.core.data.network.RemoteConfigurationDataSourceImpl
import com.roman.moviemania.core.data.network.RemoteDiscoverDataSource
import com.roman.moviemania.core.data.network.RemoteDiscoverDataSourceImpl
import com.roman.moviemania.core.data.network.utils.HttpClientFactory
import com.roman.moviemania.core.data.repository.ConfigurationRepositoryImpl
import com.roman.moviemania.core.data.repository.DiscoverRepositoryImpl
import com.roman.moviemania.core.domain.repository.ConfigurationRepository
import com.roman.moviemania.core.domain.repository.DiscoverRepository
import com.roman.moviemania.genre.data.network.RemoteGenreDataSource
import com.roman.moviemania.genre.data.network.RemoteGenreDataSourceImpl
import com.roman.moviemania.genre.data.repository.GenreRepositoryImpl
import com.roman.moviemania.genre.domain.repository.GenreRepository
import com.roman.moviemania.genre.presentation.GenreViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(engine = CIO.create()) }

    singleOf(::RemoteGenreDataSourceImpl).bind<RemoteGenreDataSource>()
    singleOf(::RemoteConfigurationDataSourceImpl).bind<RemoteConfigurationDataSource>()
    singleOf(::RemoteDiscoverDataSourceImpl).bind<RemoteDiscoverDataSource>()

    singleOf(::GenreRepositoryImpl).bind<GenreRepository>()
    singleOf(::ConfigurationRepositoryImpl).bind<ConfigurationRepository>()
    singleOf(::DiscoverRepositoryImpl).bind<DiscoverRepository>()

    viewModelOf(::GenreViewModel)
}