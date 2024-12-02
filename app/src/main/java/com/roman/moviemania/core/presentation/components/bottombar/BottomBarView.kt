package com.roman.moviemania.core.presentation.components.bottombar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun BottomBarView(
    selectedItemIndex: Int,
    onNavItemClick: (Int) -> Unit,
    items: List<BottomBarItemModel>,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier
    ) {
        items.forEachIndexed { index, item ->
            val selected = selectedItemIndex == index

            NavigationBarItem(
                selected = selected,
                onClick = {
                    onNavItemClick(index)
                },
                label = {
                    Text(text = stringResource(item.title))
                },
                icon = {
                    Icon(
                        imageVector = if (selected) {
                            item.selectedIcon
                        } else {
                            item.unselectedIcon
                        },
                        contentDescription = stringResource(item.title)
                    )
                }
            )
        }
    }
}