package com.app.vegsty.ui.screens.onboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vegsty.ui.model.ExceptionHandler
import com.app.vegsty.ui.model.UiEvent
import com.app.vegsty.ui.route.NavigationType
import com.app.vegsty.ui.route.Route
import com.app.vegsty.ui.route.RouteArgument
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor(
) : ViewModel() {
  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onEvent(onboardScreenUiEvent: OnboardScreenUiEvent) {
    when (onboardScreenUiEvent) {
      is OnboardScreenUiEvent.OnGetStartedClick -> {
        onGetStartedClick()
      }
      is OnboardScreenUiEvent.OnLogInClick -> {
        onLogInClick()
      }
    }
  }

  private fun onGetStartedClick() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(
        UiEvent.Navigate(
          navigationType = NavigationType.ClearBackStackNavigate(Route.SCREEN_WELCOME.name),
          data = mapOf(
            RouteArgument.ARG_WELCOME_IS_LOGIN_SCREEN.name to false
          )
        )
      )
    }
  }

  private fun onLogInClick() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(
        UiEvent.Navigate(
          navigationType = NavigationType.ClearBackStackNavigate(Route.SCREEN_WELCOME.name),
          data = mapOf(
            RouteArgument.ARG_WELCOME_IS_LOGIN_SCREEN.name to true
          )
        )
      )
    }
  }
}