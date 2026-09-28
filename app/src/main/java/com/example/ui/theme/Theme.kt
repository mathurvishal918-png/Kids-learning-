package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = KidsPrimary,
    onPrimary = Color.White,
    primaryContainer = KidsPrimaryLight,
    onPrimaryContainer = Color.White,
    secondary = KidsSecondary,
    onSecondary = Color.White,
    tertiary = KidsTertiary,
    onTertiary = Color(0xFF261900),
    background = KidsBackgroundLight,
    onBackground = KidsTextPrimary,
    surface = KidsSurfaceLight,
    onSurface = KidsTextPrimary,
    surfaceVariant = KidsSurfaceVariantLight,
    onSurfaceVariant = KidsTextSecondary
)

private val DarkColorScheme = darkColorScheme(
    primary = KidsPrimaryLight,
    onPrimary = Color.Black,
    secondary = KidsSecondary,
    onSecondary = Color.White,
    tertiary = KidsTertiary,
    onTertiary = Color.Black,
    background = KidsBackgroundDark,
    onBackground = Color.White,
    surface = KidsSurfaceDark,
    onSurface = Color.White
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
