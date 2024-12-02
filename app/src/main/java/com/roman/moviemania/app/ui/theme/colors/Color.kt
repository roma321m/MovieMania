package com.roman.moviemania.app.ui.theme.colors

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val md_theme_light_primary = Color(0xFF006992) // Blue
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFCCE5FF) // Light Blue
val md_theme_light_onPrimaryContainer = Color(0xFF001F2B)
val md_theme_light_secondary = Color(0xFF00608F) // Darker Blue
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFCCE4FF) // Another Light Blue
val md_theme_light_onSecondaryContainer = Color(0xFF001D29)
val md_theme_light_tertiary = Color(0xFF00678D) // Bluish-Green
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFBCEBFF) // Light Bluish-Green
val md_theme_light_onTertiaryContainer = Color(0xFF00212C)
val md_theme_light_error = Color(0xFFBA1A1A) // Keep error red for visibility
val md_theme_light_errorContainer = Color(0xFFFFDAD6)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410002)
val md_theme_light_background = Color(0xFFF8FDFF) // Light background
val md_theme_light_onBackground = Color(0xFF001F25)
val md_theme_light_surface = Color(0xFFF8FDFF)
val md_theme_light_onSurface = Color(0xFF001F25)
val md_theme_light_surfaceVariant = Color(0xFFDCE4EB) // Light gray
val md_theme_light_onSurfaceVariant = Color(0xFF43474E)
val md_theme_light_outline = Color(0xFF73777F) // Grayish outline
val md_theme_light_inverseOnSurface = Color(0xFFF1F0F4)
val md_theme_light_inverseSurface = Color(0xFF2F3033)
val md_theme_light_inversePrimary = Color(0xFF00618A) // Darker blue
val md_theme_light_shadow = Color(0xFF000000)
val md_theme_light_surfaceTint = Color(0xFF006992) // Match primary
val md_theme_light_outlineVariant = Color(0xFFC3C7CF) // Lighter gray
val md_theme_light_scrim = Color(0xFF000000)

// Dark theme adjustments, keeping a similar blue tone but darker
val md_theme_dark_primary = Color(0xFF7AD6FF) // Lighter blue for contrast
val md_theme_dark_onPrimary = Color(0xFF00354D)
val md_theme_dark_primaryContainer = Color(0xFF004E70)
val md_theme_dark_onPrimaryContainer = Color(0xFFCCE5FF)
val md_theme_dark_secondary = Color(0xFF6CD3FF) // Another lighter blue
val md_theme_dark_onSecondary = Color(0xFF00344C)
val md_theme_dark_secondaryContainer = Color(0xFF004D6F)
val md_theme_dark_onSecondaryContainer = Color(0xFFCCE4FF)
val md_theme_dark_tertiary = Color(0xFF5FD9FF) // Light bluish-green
val md_theme_dark_onTertiary = Color(0xFF00364B)
val md_theme_dark_tertiaryContainer = Color(0xFF004F6E)
val md_theme_dark_onTertiaryContainer = Color(0xFFBCEBFF)
val md_theme_dark_error = Color(0xFFFFB4AB) // Keep error distinct
val md_theme_dark_errorContainer = Color(0xFF93000A)
val md_theme_dark_onError = Color(0xFF690005)
val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6)
val md_theme_dark_background = Color(0xFF001F25) // Dark background
val md_theme_dark_onBackground = Color(0xFFA6EEFF)
val md_theme_dark_surface = Color(0xFF001F25)
val md_theme_dark_onSurface = Color(0xFFA6EEFF)
val md_theme_dark_surfaceVariant = Color(0xFF43474E) // Darker gray
val md_theme_dark_onSurfaceVariant = Color(0xFFC3C7CF)
val md_theme_dark_outline = Color(0xFF8D9199) // Darker outline
val md_theme_dark_inverseOnSurface = Color(0xFF001F25)
val md_theme_dark_inverseSurface = Color(0xFFA6EEFF)
val md_theme_dark_inversePrimary = Color(0xFF7AD6FF) // Match light primary
val md_theme_dark_shadow = Color(0xFF000000)
val md_theme_dark_surfaceTint = Color(0xFF7AD6FF) // Match primary
val md_theme_dark_outlineVariant = Color(0xFF43474E) // Darker gray
val md_theme_dark_scrim = Color(0xFF000000)

val seed = Color(0xFF006992) // Base the seed on the primary blue

val ColorScheme.rateGreen: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF127217) else Color(0xFF4CAF50)

val ColorScheme.rateRed: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF721212) else Color(0xFFF44336)

val ColorScheme.rateOrange: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF725212) else Color(0xFFFF9800)