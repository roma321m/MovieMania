package com.roman.moviemania.explore.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ExploreScreen(
    uiState: ExploreUiState,
    onAction: (ExploreAction) -> Unit,
    navigationBar: @Composable () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            navigationBar()
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Explore Screen"
            )
        }
    }
}