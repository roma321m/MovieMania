package com.roman.moviemania.genre.presentation.grid.views.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.roman.moviemania.R
import com.roman.moviemania.core.domain.model.DiscoverSortOptions
import com.roman.moviemania.genre.presentation.GenreAction
import com.roman.moviemania.genre.presentation.GenreUiState
import com.roman.moviemania.genre.presentation.utils.asStrRes


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
                            text = stringResource(sortOption.asStrRes())
                        )
                    },
                    enabled = uiState.sortBy != sortOption,
                    colors = MenuDefaults.itemColors(
                        textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledTextColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }
}