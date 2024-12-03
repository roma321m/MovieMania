package com.roman.moviemania.app.navigation.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Explore
import com.roman.moviemania.R
import com.roman.moviemania.app.navigation.routes.Route
import com.roman.moviemania.core.presentation.components.bottombar.BottomBarItemModel

object NavigationBarDefaults {

    val items = listOf(
        BottomBarItemModel(
            route = Route.Explore,
            title = R.string.explore_title,
            selectedIcon = Icons.Filled.Explore,
            unselectedIcon = Icons.Outlined.Explore,
        ),
        BottomBarItemModel(
            route = Route.Genre,
            title = R.string.genre_top_bar_title,
            selectedIcon = Icons.Filled.Category,
            unselectedIcon = Icons.Outlined.Category,
        ),
    )

}