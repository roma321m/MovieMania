package com.roman.moviemania.explore.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.roman.moviemania.core.domain.model.Movie
import com.roman.moviemania.core.presentation.components.AsyncImageView

@Composable
fun ExploreMovieItemView(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImageView(
            imageUrl = movie.posterPath,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .weight(1f)
        )

        Text(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            text = movie.title,
            maxLines = 2,
            minLines = 2
        )
    }
}