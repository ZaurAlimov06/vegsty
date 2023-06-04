package com.app.vegsty.ui.screens.restaurants.main

import com.app.vegsty.data.remote.dto.Restaurant

data class RestaurantUiState(
  val restaurantList: List<Restaurant> = listOf()
)
