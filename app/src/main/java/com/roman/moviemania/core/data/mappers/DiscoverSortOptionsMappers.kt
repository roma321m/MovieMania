package com.roman.moviemania.core.data.mappers

import com.roman.moviemania.core.domain.model.DiscoverSortOptions

fun DiscoverSortOptions.toDtoString(): String {
    return when (this) {
        DiscoverSortOptions.POPULARITY -> "popularity.desc"
        DiscoverSortOptions.REVENUE -> "revenue.desc"
        DiscoverSortOptions.PRIMARY_RELEASE_DATE -> "primary_release_date.desc"
        DiscoverSortOptions.VOTE_AVERAGE -> "vote_average.desc"
    }
}