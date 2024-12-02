@file:OptIn(ExperimentalMaterial3Api::class)

package com.roman.moviemania.core.presentation.components.topbar

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun TopBarView(
    model: TopBarModel,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(model.title)
            )
        },
        actions = {
            model.actions.forEach {
                TopBarActionView(model = it)
            }
        }
    )
}


@Composable
private fun TopBarActionView(
    model: TopBarActionModel
) {
    IconButton(
        onClick = {
            model.onClick?.invoke()
        }
    ) {
        Icon(
            imageVector = model.icon,
            contentDescription = stringResource(model.title)
        )

        model.dropDownContent?.let {
            DropdownMenuView(model.dropDownContent)
        }
    }
}

@Composable
private fun DropdownMenuView(
    content: @Composable () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        content()
    }
}