package com.roman.moviemania.genre.presentation.grid.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.roman.moviemania.app.ui.theme.MovieManiaTheme
import com.roman.moviemania.genre.domain.Genre


@Composable
fun GenreGridTabRowView(
    genres: List<Genre>,
    onGenreSelected: (Genre) -> Unit,
    modifier: Modifier = Modifier,
    selectedGenre: Genre? = null,
) {
    if (genres.isNotEmpty()) {
        ScrollableTabRow(
            modifier = modifier.fillMaxWidth(),
            edgePadding = 16.dp,
            selectedTabIndex = genres.indexOf(selectedGenre),
            divider = {}
        ) {
            genres.forEach { genre ->
                GenreTabView(
                    genre = genre,
                    selected = genre == selectedGenre,
                    onClick = {
                        onGenreSelected(genre)
                    }
                )
            }
        }
    }
}

@Composable
private fun GenreTabView(
    genre: Genre,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Tab(
        selected = selected,
        onClick = onClick,
    ) {
        Text(
            modifier = Modifier.padding(
                vertical = 12.dp,
                horizontal = 8.dp
            ),
            text = genre.name,
            maxLines = 1,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@PreviewLightDark
@Composable
fun GenreGridTabRowViewPreview() {
    MovieManiaTheme {
        Surface {
            GenreGridTabRowView(
                listOf(
                    Genre(0, "Action"),
                    Genre(1, "Comedy"),
                    Genre(2, "Drama"),
                ),
                selectedGenre = Genre(0, "Action"),
                onGenreSelected = {}
            )
        }
    }
}