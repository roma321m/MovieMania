package com.roman.moviemania.genre.presentation.grid.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.roman.moviemania.app.ui.theme.MovieManiaTheme
import com.roman.moviemania.core.presentation.components.AsyncImageView
import com.roman.moviemania.genre.domain.Movie

@Composable
fun GenreMovieCardView(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(9f / 16f),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
            ) {
                AsyncImageView(
                    imageUrl = movie.posterPath,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxSize()
                )
            }
            RateView(
                rate = movie.voteAverage.toInt(),
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxHeight(0.2f)
                    .aspectRatio(1f)
                    .align(Alignment.BottomStart)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = " (${movie.releaseDate})",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@PreviewLightDark
@Composable
fun GenreMovieCardViewPreview() {
    MovieManiaTheme {
        Surface {
            GenreMovieCardView(
                movie = Movie(
                    id = 1,
                    title = "Movie 1",
                    overview = "Overview 1",
                    adult = false,
                    originalLanguage = "en",
                    popularity = 1f,
                    voteCount = 1,
                    voteAverage = 7f,
                    releaseDate = "2023",
                    posterPath = "https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
                )
            )
        }
    }
}