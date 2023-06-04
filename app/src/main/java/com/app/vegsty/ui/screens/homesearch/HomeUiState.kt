package com.app.vegsty.ui.screens.homesearch

import com.app.vegsty.data.remote.dto.Recipe

data class HomeUiState(
  val searchText: String = "",
  val recipeList: List<Recipe> = listOf(),
  val filteredRecipeList: List<Recipe> = listOf()
)