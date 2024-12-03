package com.roman.moviemania.genre.presentation

import com.roman.moviemania.core.domain.model.DiscoverSortOptions
import com.roman.moviemania.core.domain.model.Movie
import com.roman.moviemania.genre.domain.model.Genre

sealed interface GenreAction {
    data class OnMoreActionClick(val expanded: Boolean) : GenreAction
    data object OnPrivacyPolicyClick : GenreAction
    data object OnTermsAndConditionsClick : GenreAction
    data object OnAboutClicked : GenreAction
    data class OnSortActionClick(val expanded: Boolean) : GenreAction
    data class OnSortOptionClick(val sort: DiscoverSortOptions) : GenreAction
    data class OnGenreSelected(val genre: Genre) : GenreAction
    data class OnMovieSelected(val movie: Movie) : GenreAction
}