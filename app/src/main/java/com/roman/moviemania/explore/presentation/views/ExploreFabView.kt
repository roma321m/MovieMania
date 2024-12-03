package com.roman.moviemania.explore.presentation.views

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.roman.moviemania.R

@Composable
fun ExploreFabView(
    onFabClick: () -> Unit,
    scrollState: LazyGridState,
    modifier: Modifier = Modifier
) {
    val expanded by remember {
        derivedStateOf { scrollState.firstVisibleItemIndex == 0 }
    }

    ExtendedFloatingActionButton(
        modifier = modifier,
        expanded = expanded,
        onClick = onFabClick,
        icon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.genre_fab_search)
            )
        },
        text = {
            Text(text = stringResource(R.string.genre_fab_search))
        }
    )
}