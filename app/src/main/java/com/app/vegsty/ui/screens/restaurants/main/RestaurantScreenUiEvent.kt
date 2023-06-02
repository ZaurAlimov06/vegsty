package com.app.vegsty.ui.screens.restaurants.main

sealed class RestaurantScreenUiEvent {
    object OnFirstRestaurantClick : RestaurantScreenUiEvent()
    object OnSecondRestaurantClick : RestaurantScreenUiEvent()
}