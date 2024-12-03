package com.roman.moviemania.explore.presentation.views.topbar

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
import com.roman.moviemania.explore.presentation.ExploreAction
import com.roman.moviemania.explore.presentation.ExploreUiState

@Composable
fun ExploreTopBarMoreActionView(
    uiState: ExploreUiState,
    onAction: (ExploreAction) -> Unit
) {
    IconButton(
        onClick = {
            onAction(ExploreAction.OnMoreActionClick(true))
        }
    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = stringResource(R.string.top_bar_action_more)
        )
        DropdownMenu(
            expanded = uiState.showMoreMenu,
            onDismissRequest = {
                onAction(ExploreAction.OnMoreActionClick(false))
            }
        ) {
            DropdownMenuItem(
                onClick = {
                    onAction(ExploreAction.OnPrivacyPolicyClick)
                },
                text = {
                    Text(text = stringResource(id = R.string.top_bar_more_menu_privacy_policy))
                }
            )
            DropdownMenuItem(
                onClick = {
                    onAction(ExploreAction.OnTermsAndConditionsClick)
                },
                text = {
                    Text(text = stringResource(id = R.string.top_bar_more_menu_terms_and_conditions))
                }
            )
            DropdownMenuItem(
                onClick = {
                    onAction(ExploreAction.OnAboutClicked)
                },
                text = {
                    Text(text = stringResource(id = R.string.top_bar_more_menu_about))
                }
            )
        }
    }
}