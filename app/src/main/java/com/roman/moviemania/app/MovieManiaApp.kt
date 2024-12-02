package com.roman.moviemania.app

import android.app.Application
import com.roman.moviemania.app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieManiaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieManiaApp)
            androidLogger()

            modules(appModule)
        }
    }

}