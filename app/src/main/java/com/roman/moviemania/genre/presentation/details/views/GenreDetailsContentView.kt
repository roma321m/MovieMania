@file:OptIn(ExperimentalLayoutApi::class)

package com.roman.moviemania.genre.presentation.details.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.roman.moviemania.R
import com.roman.moviemania.core.domain.model.Movie
import com.roman.moviemania.genre.domain.model.Genre

@Composable
fun GenreDetailsContentView(
    movie: Movie,
    modifier: Modifier = Modifier,
    genres: List<Genre> = emptyList(),
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (movie.title.isNotBlank()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = movie.title,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(16.dp))
        }

        if (movie.releaseDate.isNotBlank()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "${stringResource(R.string.genre_details_release_date)} (${movie.releaseDate})",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(16.dp))
        }

        if (movie.adult) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.genre_details_adult),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(16.dp))
        }


        if (movie.overview.isNotBlank()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.genre_details_overview),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = movie.overview,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(16.dp))
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.genre_details_genres),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
        ) {
            movie.genreIds.forEach { id ->
                genres.find { it.id == id }?.let {
                    GenreChipView {
                        Text(text = it.name)
                    }
                }
            }
        }
        Spacer(Modifier.height(12.dp))
    }
}