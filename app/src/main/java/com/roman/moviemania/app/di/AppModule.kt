package com.roman.moviemania.app.di

import com.roman.moviemania.core.data.network.HttpClientFactory
import com.roman.moviemania.genre.data.network.RemoteGenreDataSource
import com.roman.moviemania.genre.data.network.RemoteGenreDataSourceImpl
import com.roman.moviemania.genre.data.repository.GenreRepositoryImpl
import com.roman.moviemania.genre.domain.GenreRepository
import com.roman.moviemania.genre.presentation.GenreViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(engine = CIO.create()) }
    singleOf(::RemoteGenreDataSourceImpl).bind<RemoteGenreDataSource>()
    singleOf(::GenreRepositoryImpl).bind<GenreRepository>()

    viewModelOf(::GenreViewModel)
}