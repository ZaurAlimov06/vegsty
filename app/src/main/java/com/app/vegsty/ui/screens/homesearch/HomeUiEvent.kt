package com.app.vegsty.ui.screens.homesearch

import com.app.vegsty.data.remote.dto.Recipe

sealed class HomeUiEvent {
  data class OnRecipeClick(val recipe: Recipe) : HomeUiEvent()
  object GetAllRecipes : HomeUiEvent()
}
