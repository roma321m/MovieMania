package com.roman.moviemania.core.presentation.components.bottombar

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.roman.moviemania.app.navigation.routes.Route

data class BottomBarItemModel(
    val route: Route,
    @StringRes val title: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)