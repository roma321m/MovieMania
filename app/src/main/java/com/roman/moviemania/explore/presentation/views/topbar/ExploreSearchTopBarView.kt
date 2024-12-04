@file:OptIn(ExperimentalMaterial3Api::class)

package com.roman.moviemania.explore.presentation.views.topbar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import com.roman.moviemania.R
import com.roman.moviemania.explore.presentation.ExploreAction
import com.roman.moviemania.explore.presentation.ExploreUiState
import com.roman.moviemania.explore.presentation.views.ExploreSearchBarContentView


@Composable
fun ExploreSearchTopBarView(
    uiState: ExploreUiState,
    onAction: (ExploreAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    SearchBar(
        modifier = modifier
            .fillMaxWidth(),
        inputField = {
            SearchBarDefaults.InputField(
                query = uiState.searchQuery,
                onQueryChange = {
                    onAction(ExploreAction.OnSearchQueryChange(it))
                },
                onSearch = {
                    keyboardController?.hide()
                },
                expanded = uiState.expendedSearch,
                onExpandedChange = {
                    onAction(ExploreAction.OnSearchExpanded(it))
                },
                placeholder = {
                    Text(stringResource(R.string.top_bar_search))
                },
                leadingIcon = {
                    IconButton(
                        onClick = {
                            keyboardController?.hide()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            onAction(ExploreAction.OnSearchQueryChange(""))
                            onAction(ExploreAction.OnHideSearchBar)
                        }
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = null
                        )
                    }
                },
            )
        },
        onExpandedChange = {
            onAction(ExploreAction.OnSearchExpanded(it))
        },
        expanded = uiState.expendedSearch
    ) {
        ExploreSearchBarContentView(
            list = uiState.searchResults,
            modifier = Modifier.fillMaxSize()
        )
    }
}