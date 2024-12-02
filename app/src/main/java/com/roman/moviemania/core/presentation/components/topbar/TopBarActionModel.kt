package com.roman.moviemania.core.presentation.components.topbar

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class TopBarActionModel(
    @StringRes val title: Int,
    val icon: ImageVector,
    val onClick: (() -> Unit)? = null,
    val dropDownContent: @Composable (() -> Unit)? = null,
)