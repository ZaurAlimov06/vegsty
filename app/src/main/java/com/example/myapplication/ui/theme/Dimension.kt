package com.example.myapplication.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimension(

  val spaceListItemPadding: Dp = 30.dp,
  val spaceScreenHorizontalPadding: Dp = 30.dp,
  val spaceScreenVerticalPadding: Dp = 10.dp,
)

val LocalSpacing = compositionLocalOf { Dimension() }