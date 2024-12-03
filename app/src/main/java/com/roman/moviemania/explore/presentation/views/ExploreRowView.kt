package com.roman.moviemania.explore.presentation.views

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.roman.moviemania.core.domain.model.Movie

@Composable
fun ColumnScope.ExploreRowView(
    @StringRes title: Int,
    list: List<Movie>
) {

    Text(
        text = stringResource(title),
        textAlign = TextAlign.Start,
        maxLines = 1,
        minLines = 1,
        style = MaterialTheme.typography.titleMedium
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = list,
            key = {
                it.id
            }
        ) { movie ->
            ExploreMovieItemView(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(9f / 16f),
                movie = movie
            )
        }
    }

}