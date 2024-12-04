package com.roman.moviemania.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import coil3.compose.rememberAsyncImagePainter
import com.roman.moviemania.R

@Composable
fun AsyncImageView(
    imageUrl: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    isPreview: Boolean = LocalInspectionMode.current
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        var imageLoadResult by remember {
            mutableStateOf<Result<Painter>?>(null)
        }
        val painter = rememberAsyncImagePainter(
            model = imageUrl,
            onSuccess = {
                val size = it.painter.intrinsicSize
                imageLoadResult = if (size.width > 1 && size.height > 1) {
                    Result.success(it.painter)
                } else {
                    Result.failure(Exception("Invalid image size"))
                }
            },
            onError = {
                it.result.throwable.printStackTrace()
                imageLoadResult = Result.failure(it.result.throwable)
            }
        )

        when (val result = imageLoadResult) {
            null -> PulseAnimationView(
                Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(1f)
            )

            else -> {
                if (isPreview) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(R.drawable.img_previews),
                        contentDescription = description,
                        contentScale = ContentScale.Crop
                    )
                } else if (result.isSuccess) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painter,
                        contentDescription = description,
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.surfaceContainer),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondaryContainer),
                        painter = painterResource(R.drawable.ic_image),
                        contentDescription = description,
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}