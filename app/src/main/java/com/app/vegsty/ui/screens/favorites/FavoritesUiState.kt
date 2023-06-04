package com.app.vegsty.ui.screens.favorites

import com.app.vegsty.data.remote.dto.Recipe

data class FavoritesUiState(
  val favoriteRecipes: List<Recipe?> = listOf()
)
