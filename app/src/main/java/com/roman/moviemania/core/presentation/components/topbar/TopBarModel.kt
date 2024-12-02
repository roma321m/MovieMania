package com.roman.moviemania.core.presentation.components.topbar

import androidx.annotation.StringRes

data class TopBarModel(
    @StringRes val title: Int,
    val actions: List<TopBarActionModel>,
)