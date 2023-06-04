package com.app.vegsty.ui.screens.restaurants.main

import com.app.vegsty.data.remote.dto.Restaurant

sealed class RestaurantScreenUiEvent {
  data class OnRestaurantClick(val restaurant: Restaurant) : RestaurantScreenUiEvent()
  object GetAllRestaurants : RestaurantScreenUiEvent()
}