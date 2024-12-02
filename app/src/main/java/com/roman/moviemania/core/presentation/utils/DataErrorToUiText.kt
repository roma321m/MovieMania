package com.roman.moviemania.core.presentation.utils

import com.roman.moviemania.R
import com.roman.moviemania.core.domain.utils.DataError

fun DataError.asUiText(): UiText {
    val resourceId = when (this) {
        DataError.Local.NO_STORAGE_ACCESS -> R.string.error_no_storage_access
        DataError.Local.NO_STORAGE_SPACE -> R.string.error_no_storage_space
        DataError.Local.UNKNOWN -> R.string.error_unknown
        DataError.Network.REQUEST_TIMEOUT -> R.string.error_request_timeout
        DataError.Network.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        DataError.Network.NO_INTERNET_CONNECTION -> R.string.error_no_internet_connection

        DataError.Network.SERVER_ERROR,
        DataError.Network.SERIALIZATION,
        DataError.Network.UNKNOWN -> R.string.error_unknown
    }
    return UiText.StringResource(resourceId)
}