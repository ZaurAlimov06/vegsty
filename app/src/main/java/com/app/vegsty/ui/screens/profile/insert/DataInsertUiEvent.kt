package com.app.vegsty.ui.screens.profile.insert

sealed class DataInsertUiEvent {
  object OnInsertRecipe : DataInsertUiEvent()
  object OnInsertRestaurant : DataInsertUiEvent()
  data class OnUpdateRecipeImgUrl(val recipeImgUrl: String) : DataInsertUiEvent()
  data class OnUpdateRecipeDetail(val recipeDetail: String) : DataInsertUiEvent()
  data class OnUpdateRecipeTitle(val recipeTitle: String) : DataInsertUiEvent()
  data class OnUpdateRecipeProtein(val recipeProtein: String) : DataInsertUiEvent()
  data class OnUpdateRecipeCalorie(val recipeCalorie: String) : DataInsertUiEvent()
  data class OnUpdateRecipeFat(val recipeFat: String) : DataInsertUiEvent()
  data class OnUpdateRecipeCarbs(val recipeCarbs: String) : DataInsertUiEvent()
  data class OnUpdateRestaurantName(val restaurantName: String) : DataInsertUiEvent()
}