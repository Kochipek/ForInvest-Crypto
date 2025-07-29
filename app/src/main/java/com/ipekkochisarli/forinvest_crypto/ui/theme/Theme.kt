package com.ipekkochisarli.forinvest_crypto.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White

private val LightColors = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = White,
    secondary = Green,
    onSecondary = White,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    outline = DarkOutline,
)

private val DarkColors = darkColorScheme(
    primary = PrimaryBlue,
    onPrimary = Black,
    secondary = Green,
    onSecondary = Black,
    background = DarkBackground,
    onBackground = White,
    surface = Gray,
    onSurface = White,
    outline = DarkOutline,
)

@Composable
fun ForInvestCryptoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}