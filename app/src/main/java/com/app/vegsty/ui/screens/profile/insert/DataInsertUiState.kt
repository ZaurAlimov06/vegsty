package com.app.vegsty.ui.screens.profile.insert

data class DataInsertUiState(
  val recipeImgUrl: String = "",
  val recipeDetail: String = "",
  val recipeTitle: String = "",
  val recipeProtein: String = "",
  val recipeCalorie: String = "",
  val recipeFat: String = "",
  val recipeCarbs: String = "",
  val isRecipeButtonEnabled: Boolean = false,

  val restaurantName: String = "",
  val restaurantDetail: String = "",
  val restaurantAddress: String = "",
  val restaurantPhoto: String = "",
  val isRestaurantButtonEnabled: Boolean = false
)