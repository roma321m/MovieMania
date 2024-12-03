package com.roman.moviemania.app.ui.previews

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.roman.moviemania.core.domain.model.Movie
import com.roman.moviemania.explore.presentation.ExploreUiState

class ExploreUiStatePreviewParameterProvider : PreviewParameterProvider<ExploreUiState> {

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

    private val loadingUiState = ExploreUiState(
        isLoading = true,
    )

    private val searchActive = ExploreUiState(
        isLoading = false,
        searchActive = true,
        searchQuery = "Avengers"
    )

    private val searchExpended = ExploreUiState(
        isLoading = false,
        searchActive = true,
        expendedSearch = true,
        searchQuery = "Avengers"
    )

    private val withMovies = ExploreUiState(
        isLoading = false,
        searchActive = false,
        expendedSearch = false,
        searchQuery = "",
        nowPlayingMovies = movies,
        topRated = movies,
        popular = movies,
        upcoming = movies
    )

    override val values: Sequence<ExploreUiState> = sequenceOf(
        loadingUiState,
        searchActive,
        searchExpended,
        withMovies
    )
}