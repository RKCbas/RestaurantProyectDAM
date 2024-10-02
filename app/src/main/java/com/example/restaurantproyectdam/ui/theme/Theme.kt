package com.example.restaurantproyectdam.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    /*primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,*/

    primary = DPrimary,
    onPrimary = DOnPrimary,
    secondary = DSecondary,
    onSecondary = DOnSecondary,
    error=DError,
    onError = DOnError,
    tertiary = DTertiary,
    onTertiary = DOnTertiary,
    primaryContainer = DPrimaryContainer,
    onPrimaryContainer = DOnPrimaryContainer,
    secondaryContainer = DSecondaryContainer,
    onSecondaryContainer = DOnSecondaryContainer,
    tertiaryContainer = DTertiaryContainer,
    onTertiaryContainer = DOnTertiaryContainer,
    errorContainer = DErrorContainer,
    onErrorContainer = DOnErrorContainer,
    surfaceDim = DSurfaceDim,
    surface = DSurface,
    surfaceBright = DSurfaceBright,
    inverseSurface = DInverseSurface,
    surfaceContainerLowest = DSurfaceContainerLowest,
    surfaceContainerLow = DSurfaceContainerLow,
    surfaceContainer = DSurfaceContainer,
    surfaceContainerHigh = DSurfaceContainerHigh,
    surfaceContainerHighest = DSurfaceContainerHighest,
    inverseOnSurface = DInverseOnSurface,
    inversePrimary = DInversePrimary,
    onSurface = DOnSurface,
    onSurfaceVariant = DOnSurfaceVariant,
    outline = DOutline,
    outlineVariant = DOutlineVariant,
    scrim = DScrim,
)

private val LightColorScheme = lightColorScheme(
    /*primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,*/

    primary = Primary,
    onPrimary = OnPrimary,
    secondary = Secondary,
    onSecondary = OnSecondary,
    error = Error,
    onError = OnError,
    tertiary = Tertiary,
    onTertiary = OnTertiary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,
    secondaryContainer = SecondaryContainer,
    onSecondaryContainer = OnSecondaryContainer,
    tertiaryContainer = TertiaryContainer,
    onTertiaryContainer = OnTertiaryContainer,
    errorContainer = ErrorContainer,
    onErrorContainer = OnErrorContainer,
    surfaceDim = SurfaceDim,
    surface = Surface,
    surfaceBright = SurfaceBright,
    inverseSurface = InverseSurface,
    surfaceContainerLowest = SurfaceContainerLowest,
    surfaceContainerLow = SurfaceContainerLow,
    surfaceContainer = SurfaceContainer,
    surfaceContainerHigh = SurfaceContainerHigh,
    surfaceContainerHighest = SurfaceContainerHighest,
    inverseOnSurface = InverseOnSurface,
    inversePrimary = InversePrimary,
    onSurface = OnSurface,
    onSurfaceVariant = OnSurfaceVariant,
    outline = Outline,
    outlineVariant = OutlineVariant,
    scrim = Scrim,




    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun RestaurantProyectDAMTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}