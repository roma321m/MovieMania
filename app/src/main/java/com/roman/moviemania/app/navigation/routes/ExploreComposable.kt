package com.roman.moviemania.app.navigation.routes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavGraphBuilder.exploreComposable() {
    composable<Route.Explore> {
        // TODO: ExploreScreen()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Explore Screen"
            )
        }
    }
}