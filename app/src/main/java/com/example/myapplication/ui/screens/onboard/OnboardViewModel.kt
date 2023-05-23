package com.example.myapplication.ui.screens.onboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.model.ExceptionHandler
import com.example.myapplication.ui.model.UiEvent
import com.example.myapplication.ui.route.NavigationType
import com.example.myapplication.ui.route.Route
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
    }
  }

  private fun onGetStartedClick() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(
        UiEvent.Navigate(
          navigationType = NavigationType.ClearBackStackNavigate(Route.SCREEN_WELCOME.name),
          data = mapOf<String, Any>()
        )
      )
    }
  }
}