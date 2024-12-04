package com.roman.moviemania.app.di

import com.roman.moviemania.app.launcher.ActivityLauncher
import com.roman.moviemania.app.navigation.MainNavigator
import com.roman.moviemania.app.navigation.Navigator
import com.roman.moviemania.app.navigation.routes.Route
import com.roman.moviemania.app.presentation.AppViewModel
import com.roman.moviemania.core.data.network.RemoteConfigurationDataSource
import com.roman.moviemania.core.data.network.RemoteConfigurationDataSourceImpl
import com.roman.moviemania.core.data.network.RemoteDiscoverDataSource
import com.roman.moviemania.core.data.network.RemoteDiscoverDataSourceImpl
import com.roman.moviemania.core.data.network.utils.HttpClientFactory
import com.roman.moviemania.core.data.repository.ConfigurationRepositoryImpl
import com.roman.moviemania.core.data.repository.DiscoverRepositoryImpl
import com.roman.moviemania.core.domain.repository.ConfigurationRepository
import com.roman.moviemania.core.domain.repository.DiscoverRepository
import com.roman.moviemania.explore.data.network.RemoteSearchDataSource
import com.roman.moviemania.explore.data.network.RemoteSearchDataSourceImpl
import com.roman.moviemania.explore.data.repository.SearchRepositoryImpl
import com.roman.moviemania.explore.domain.repository.SearchRepository
import com.roman.moviemania.explore.presentation.ExploreViewModel
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
    single { MainNavigator(startDestination = Route.Explore) }.bind<Navigator>()
    singleOf(::ActivityLauncher)

    singleOf(::RemoteGenreDataSourceImpl).bind<RemoteGenreDataSource>()
    singleOf(::RemoteConfigurationDataSourceImpl).bind<RemoteConfigurationDataSource>()
    singleOf(::RemoteDiscoverDataSourceImpl).bind<RemoteDiscoverDataSource>()
    singleOf(::RemoteSearchDataSourceImpl).bind<RemoteSearchDataSource>()

    singleOf(::GenreRepositoryImpl).bind<GenreRepository>()
    singleOf(::ConfigurationRepositoryImpl).bind<ConfigurationRepository>()
    singleOf(::DiscoverRepositoryImpl).bind<DiscoverRepository>()
    singleOf(::SearchRepositoryImpl).bind<SearchRepository>()

    viewModelOf(::GenreViewModel)
    viewModelOf(::ExploreViewModel)
    viewModelOf(::AppViewModel)
}