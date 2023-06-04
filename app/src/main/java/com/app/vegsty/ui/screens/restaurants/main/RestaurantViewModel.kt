package com.app.vegsty.ui.screens.restaurants.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vegsty.data.remote.dto.Restaurant
import com.app.vegsty.ui.model.ExceptionHandler
import com.app.vegsty.ui.model.Response
import com.app.vegsty.ui.model.UiEvent
import com.app.vegsty.ui.repository.MainRepository
import com.app.vegsty.ui.route.NavigationType
import com.app.vegsty.ui.route.Route
import com.app.vegsty.ui.route.RouteArgument
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
  private val mainRepository: MainRepository
) : ViewModel() {
  private val _uiState = MutableStateFlow(RestaurantUiState())
  val uiState: StateFlow<RestaurantUiState> = _uiState.asStateFlow()

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onEvent(restaurantScreenUiEvent: RestaurantScreenUiEvent) {
    when (restaurantScreenUiEvent) {
      is RestaurantScreenUiEvent.OnRestaurantClick -> {
        onRestaurantClickClick(restaurantScreenUiEvent.restaurant)
      }
      is RestaurantScreenUiEvent.GetAllRestaurants -> {
        getAllRestaurants()
      }
    }
  }


  private fun onRestaurantClickClick(restaurant: Restaurant) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(
        UiEvent.Navigate(
          navigationType = NavigationType.Navigate(Route.SCREEN_RESTAURANT_DETAIL.name),
          data = mapOf(
            RouteArgument.ARG_RESTAURANT_DETAIL.name to restaurant
          )
        )
      )
    }
  }

  private fun getAllRestaurants() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(UiEvent.ShowLoading)

      when (val result = mainRepository.getAllRestaurants()) {
        is Response.Fail -> {
          _uiEvent.send(UiEvent.ShowShortToast(result.exception.message))
          _uiEvent.send(UiEvent.HideLoading)
        }
        is Response.Success -> {
          _uiState.update { currentState ->
            currentState.copy(
              restaurantList = result.result
            )
          }

          _uiEvent.send(UiEvent.HideLoading)
        }
      }
    }
  }
}
