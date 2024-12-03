package com.roman.moviemania.genre.presentation.grid.views.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.roman.moviemania.R
import com.roman.moviemania.genre.presentation.GenreAction
import com.roman.moviemania.genre.presentation.GenreUiState

@Composable
fun GenreTopBarMoreActionView(
    uiState: GenreUiState,
    onAction: (GenreAction) -> Unit
) {
    IconButton(
        onClick = {
            onAction(GenreAction.OnMoreActionClick(true))
        }
    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = stringResource(R.string.top_bar_action_more)
        )
        DropdownMenu(
            expanded = uiState.showMoreMenu,
            onDismissRequest = {
                onAction(GenreAction.OnMoreActionClick(false))
            }
        ) {
            DropdownMenuItem(
                onClick = {
                    onAction(GenreAction.OnPrivacyPolicyClick)
                },
                text = {
                    Text(text = stringResource(id = R.string.top_bar_more_menu_privacy_policy))
                }
            )
            DropdownMenuItem(
                onClick = {
                    onAction(GenreAction.OnTermsAndConditionsClick)
                },
                text = {
                    Text(text = stringResource(id = R.string.top_bar_more_menu_terms_and_conditions))
                }
            )
            DropdownMenuItem(
                onClick = {
                    onAction(GenreAction.OnAboutClicked)
                },
                text = {
                    Text(text = stringResource(id = R.string.top_bar_more_menu_about))
                }
            )
        }
    }
}