package com.roman.moviemania.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Explore
import com.roman.moviemania.R
import com.roman.moviemania.core.presentation.components.bottombar.BottomBarItemModel

object NavigationBarDefaults {

    val items = listOf(
        BottomBarItemModel(
            title = R.string.explore_title,
            selectedIcon = Icons.Filled.Explore,
            unselectedIcon = Icons.Outlined.Explore,
        ),
        BottomBarItemModel(
            title = R.string.genre_title,
            selectedIcon = Icons.Filled.Category,
            unselectedIcon = Icons.Outlined.Category,
        ),
    )

}