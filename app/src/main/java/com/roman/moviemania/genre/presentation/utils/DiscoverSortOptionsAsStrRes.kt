package com.roman.moviemania.genre.presentation.utils

import com.roman.moviemania.R
import com.roman.moviemania.core.domain.model.DiscoverSortOptions

fun DiscoverSortOptions.asStrRes(): Int {
    return when (this) {
        DiscoverSortOptions.POPULARITY -> R.string.top_bar_sort_menu_popularity
        DiscoverSortOptions.REVENUE -> R.string.top_bar_sort_menu_revenue
        DiscoverSortOptions.PRIMARY_RELEASE_DATE -> R.string.top_bar_sort_menu_release_date
        DiscoverSortOptions.VOTE_AVERAGE -> R.string.top_bar_sort_menu_vote
    }
}