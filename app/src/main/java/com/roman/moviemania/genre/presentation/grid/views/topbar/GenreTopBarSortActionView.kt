package com.roman.moviemania.genre.presentation.grid.views.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.roman.moviemania.R
import com.roman.moviemania.core.domain.model.DiscoverSortOptions
import com.roman.moviemania.genre.presentation.GenreAction
import com.roman.moviemania.genre.presentation.GenreUiState


@Composable
fun GenreTopBarSortActionView(
    uiState: GenreUiState,
    onAction: (GenreAction) -> Unit
) {
    IconButton(
        onClick = {
            onAction(GenreAction.OnSortActionClick(true))
        }
    ) {
        Icon(
            imageVector = Icons.Default.FilterList,
            contentDescription = stringResource(R.string.top_bar_action_sort)
        )
        DropdownMenu(
            expanded = uiState.showSortMenu,
            onDismissRequest = {
                onAction(GenreAction.OnSortActionClick(false))
            }
        ) {
            DiscoverSortOptions.entries.forEach { sortOption ->
                DropdownMenuItem(
                    onClick = {
                        onAction(GenreAction.OnSortOptionClick(sortOption))
                    },
                    text = {
                        Text(
                            text = stringResource(
                                id = when (sortOption) {
                                    DiscoverSortOptions.POPULARITY -> R.string.top_bar_sort_menu_popularity
                                    DiscoverSortOptions.REVENUE -> R.string.top_bar_sort_menu_revenue
                                    DiscoverSortOptions.PRIMARY_RELEASE_DATE -> R.string.top_bar_sort_menu_release_date
                                    DiscoverSortOptions.VOTE_AVERAGE -> R.string.top_bar_sort_menu_vote
                                }
                            )
                        )
                    },
                    enabled = uiState.sortBy != sortOption
                )
            }
        }
    }
}