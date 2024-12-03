package com.roman.moviemania.app.navigation.routes

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Explore : Route

    @Serializable
    data object Genre : Route

}