package com.app.vegsty.ui.screens.favorites

import com.app.vegsty.data.remote.dto.Recipe

sealed class FavoritesUiEvent {
  object GetAllFavorites : FavoritesUiEvent()
  data class OnFavoriteRecipeClick(val recipe: Recipe?) : FavoritesUiEvent()
}