package com.roman.moviemania.explore.presentation

sealed interface ExploreAction {
    // top bar - more options
    data class OnMoreActionClick(val expand: Boolean) : ExploreAction
    data object OnPrivacyPolicyClick : ExploreAction
    data object OnTermsAndConditionsClick : ExploreAction
    data object OnAboutClicked : ExploreAction

    // top bar - search
    data object OnSearchFabClick : ExploreAction
    data object OnHideSearchBar : ExploreAction
    data class OnSearchQueryChange(val query: String) : ExploreAction
    data class OnSearchExpanded(val expanded: Boolean) : ExploreAction
}