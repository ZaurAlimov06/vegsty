package com.example.myapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
  primary = PrimaryColor,
  secondary = SecondaryColor,
  tertiary = TertiaryColor,
  background = Color.White,
  onBackground = BlackColor

)

private val LightColorScheme = lightColorScheme(
  primary = PrimaryColor,
  secondary = SecondaryColor,
  tertiary = TertiaryColor,
  background = Color.White,
  onBackground = BlackColor
)

@Composable
fun VegstyTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Dynamic color is available on Android 12+
  dynamicColor: Boolean = true,
  content: @Composable () -> Unit
) {
  val colorScheme = when {
    darkTheme -> DarkColorScheme
    else -> LightColorScheme
  }

  CompositionLocalProvider(
    LocalSpacing provides Dimension()
  ) {
    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      shapes = Shapes,
      content = content
    )
  }
}