package com.app.vegsty.ui.screens.restaurants.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vegsty.ui.model.ExceptionHandler
import com.app.vegsty.ui.model.UiEvent
import com.app.vegsty.ui.route.NavigationType
import com.app.vegsty.ui.route.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
) : ViewModel() {
  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onEvent(restaurantScreenUiEvent: RestaurantScreenUiEvent) {
    when (restaurantScreenUiEvent) {
      is RestaurantScreenUiEvent.OnFirstRestaurantClick -> {
        onFirstRestaurantClick()
      }

      is RestaurantScreenUiEvent.OnSecondRestaurantClick -> {
        onSecondRestaurantClick()
      }
    }
  }

  private fun onFirstRestaurantClick() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(
        UiEvent.Navigate(
          navigationType = NavigationType.Navigate(Route.SCREEN_FIRST_RESTAURANT.name),
          data = mapOf<String, Any>()
        )
      )
    }
  }

  private fun onSecondRestaurantClick() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(
        UiEvent.Navigate(
          navigationType = NavigationType.Navigate(Route.SCREEN_SECOND_RESTAURANT.name),
          data = mapOf<String, Any>()
        )
      )
    }
  }
}
