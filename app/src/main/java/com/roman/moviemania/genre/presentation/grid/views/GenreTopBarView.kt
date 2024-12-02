@file:OptIn(ExperimentalMaterial3Api::class)

package com.roman.moviemania.genre.presentation.grid.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import com.roman.moviemania.R
import com.roman.moviemania.core.presentation.components.topbar.TopBarActionModel
import com.roman.moviemania.core.presentation.components.topbar.TopBarModel
import com.roman.moviemania.core.presentation.components.topbar.TopBarView
import com.roman.moviemania.genre.presentation.GenreAction

@Composable
fun GenreTopBarView(
    scrollBehavior: TopAppBarScrollBehavior,
    onAction: (GenreAction) -> Unit,
) {
    TopBarView(
        model = TopBarModel(
            title = R.string.genre_title,
            actions = listOf(
                TopBarActionModel(
                    title = R.string.top_bar_action_sort,
                    icon = Icons.Default.FilterList,
                    dropDownContent = {
                        // todo - add drop down menu
                    }
                ),
                TopBarActionModel(
                    title = R.string.top_bar_action_more,
                    icon = Icons.Default.MoreVert,
                    dropDownContent = {
                        // todo - add drop down menu
                    }
                )
            )
        ),
        scrollBehavior = scrollBehavior,
    )
}