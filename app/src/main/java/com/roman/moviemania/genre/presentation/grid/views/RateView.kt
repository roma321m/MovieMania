package com.roman.moviemania.genre.presentation.grid.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.roman.moviemania.app.ui.theme.MovieManiaTheme
import com.roman.moviemania.app.ui.theme.colors.rateGreen
import com.roman.moviemania.app.ui.theme.colors.rateOrange
import com.roman.moviemania.app.ui.theme.colors.rateRed

@Composable
fun RateView(
    rate: Int,
    modifier: Modifier = Modifier,
    maxRate: Int = 10,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(100))
            .background(MaterialTheme.colorScheme.surfaceContainer)
    ) {
        val percentage = rate.toFloat() / maxRate.toFloat()

        CircularProgressIndicator(
            progress = {
                percentage
            },
            modifier = Modifier
                .padding(2.dp)
                .fillMaxSize(),
            color = when (percentage) {
                in 0f..0.5f -> MaterialTheme.colorScheme.rateRed
                in 0.5f..0.85f -> MaterialTheme.colorScheme.rateOrange
                else -> MaterialTheme.colorScheme.rateGreen
            },
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
            gapSize = 1.dp,
            strokeCap = StrokeCap.Butt
        )

        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = "$rate/$maxRate",
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

private class RatingPreviewParameterProvider : PreviewParameterProvider<Int> {
    override val values: Sequence<Int>
        get() = sequenceOf(3, 7, 9)
}

@PreviewLightDark
@Composable
fun RatePreview(
    @PreviewParameter(RatingPreviewParameterProvider::class) rate: Int,
) {
    MovieManiaTheme {
        Surface {
            RateView(
                rate = rate,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}