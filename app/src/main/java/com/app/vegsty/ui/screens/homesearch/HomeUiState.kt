package com.app.vegsty.ui.screens.homesearch

import com.app.vegsty.data.remote.dto.Recipe

data class HomeUiState(
  val recipeList: List<Recipe> = listOf()
)