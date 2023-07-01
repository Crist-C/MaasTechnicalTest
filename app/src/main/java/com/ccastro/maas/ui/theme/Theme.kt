package com.ccastro.maas.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(

    primary = Green200,
    onPrimary = GreenVariant40,
    primaryContainer = Green500,
    onPrimaryContainer = Green40,
    inversePrimary = GreenVariant200, // Pendiente variar

    secondary = Blue200,
    onSecondary = Blue40,
    secondaryContainer = BlueVariant700,
    onSecondaryContainer = Blue80,

    tertiary = Orange200,
    onTertiary = Orange40,
    tertiaryContainer = Orange700,
    onTertiaryContainer = Orange80,

    background = Gray700,
    onBackground = White200,
    surface = Gray500,
    onSurface = White200,
    surfaceVariant = Gray80,
    onSurfaceVariant = White200,
    surfaceTint = Green500,

    error = Red500,
    errorContainer = Red700,

    outline = Green200,
    outlineVariant = Blue200,

    scrim = Gray500,

)

private val LightColorScheme = lightColorScheme(
    primary = Green200,
    onPrimary = GreenVariant40,
    primaryContainer = Green500,
    onPrimaryContainer = Green40,
    inversePrimary = GreenVariant200, // Pendiente variar

    secondary = Blue200,
    onSecondary = Blue40,
    secondaryContainer = BlueVariant500,
    onSecondaryContainer = Blue40,

    tertiary = Orange200,
    onTertiary = Orange40,
    tertiaryContainer = Orange700,
    onTertiaryContainer = Orange80,

    background = White200,
    onBackground = Gray500,
    surface = White200,
    onSurface = Gray500,        // Color de Letra
    surfaceVariant = Green700,
    onSurfaceVariant = Green40,
    surfaceTint = Green80,

    error = Red500,
    errorContainer = Red700,

    outline = Green200,
    outlineVariant = Blue200,

    scrim = Gray500,

)

@Composable
fun MaasTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primaryContainer.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}