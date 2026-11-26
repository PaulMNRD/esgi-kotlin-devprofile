package com.example.developerprofile.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = LatteBlue,
    onPrimary = LatteBase,
    primaryContainer = LatteSurface1,
    onPrimaryContainer = LatteText,
    secondary = LatteTeal,
    onSecondary = LatteBase,
    secondaryContainer = LatteSurface1,
    onSecondaryContainer = LatteText,
    tertiary = LatteMauve,
    onTertiary = LatteBase,
    error = LatteRed,
    onError = LatteBase,
    background = LatteBase,
    onBackground = LatteText,
    surface = LatteBase,
    onSurface = LatteText,
    surfaceVariant = LatteSurface0,
    onSurfaceVariant = LatteSubtext0,
    surfaceContainer = LatteMantle,
    surfaceContainerHighest = LatteSurface0,
    outline = LatteOverlay0,
)

private val DarkColorScheme = darkColorScheme(
    primary = MochaBlue,
    onPrimary = MochaBase,
    primaryContainer = MochaSurface1,
    onPrimaryContainer = MochaText,
    secondary = MochaTeal,
    onSecondary = MochaBase,
    secondaryContainer = MochaSurface1,
    onSecondaryContainer = MochaText,
    tertiary = MochaMauve,
    onTertiary = MochaBase,
    error = MochaRed,
    onError = MochaBase,
    background = MochaBase,
    onBackground = MochaText,
    surface = MochaBase,
    onSurface = MochaText,
    surfaceVariant = MochaSurface0,
    onSurfaceVariant = MochaSubtext0,
    surfaceContainer = MochaMantle,
    surfaceContainerHighest = MochaSurface0,
    outline = MochaOverlay0,
)

@Composable
fun DeveloperProfileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
