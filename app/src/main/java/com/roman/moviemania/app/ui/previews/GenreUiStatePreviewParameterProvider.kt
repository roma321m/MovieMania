package com.roman.moviemania.app.ui.previews

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.roman.moviemania.core.domain.model.Movie
import com.roman.moviemania.genre.domain.model.Genre
import com.roman.moviemania.genre.presentation.GenreUiState

class GenreUiStatePreviewParameterProvider : PreviewParameterProvider<GenreUiState> {

    private val genres = listOf(
        Genre(0, "Action"),
        Genre(1, "Comedy"),
        Genre(2, "Drama"),
        Genre(3, "Horror"),
        Genre(4, "Romance"),
        Genre(5, "Thriller"),
    )

    private val movies = (1..20).map {
        Movie(
            id = it,
            title = "Movie $it",
            overview = "Overview $it",
            posterPath = "https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
            releaseDate = "2023",
            voteAverage = 8.5f,
            voteCount = 1000,
            popularity = 7.5f,
            originalLanguage = "en",
            adult = false,
            genreIds = listOf(1, 2, 3),
            originalTitle = "Original Title $it",
            video = false,
            backdropPath = "https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
        )
    }

    private val loadingUiState = GenreUiState(
        isLoading = true,
        genres = emptyList(),
        movies = emptyList(),
        selectedGenre = null,
        selectedMovie = null,
    )

    private val gridUiState = GenreUiState(
        isLoading = false,
        genres = genres,
        movies = movies,
        selectedGenre = genres.first(),
        selectedMovie = null,
    )

    private val detailsUiState = GenreUiState(
        isLoading = false,
        genres = genres,
        movies = movies,
        selectedGenre = genres.first(),
        selectedMovie = movies.first(),
    )

    override val values: Sequence<GenreUiState> = sequenceOf(
        loadingUiState,
        gridUiState,
        detailsUiState
    )
}