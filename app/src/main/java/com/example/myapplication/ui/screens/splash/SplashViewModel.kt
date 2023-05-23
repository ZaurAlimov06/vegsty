package com.example.myapplication.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.model.ExceptionHandler
import com.example.myapplication.ui.model.UiEvent
import com.example.myapplication.ui.route.NavigationType
import com.example.myapplication.ui.route.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
) : ViewModel() {
  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  init {
    startSplashTimer()
  }

  private fun startSplashTimer() {
    viewModelScope.launch(ExceptionHandler.handler) {
      delay(1000L)
      _uiEvent.send(
        UiEvent.Navigate(
          navigationType = NavigationType.ClearBackStackNavigate(Route.SCREEN_FIRST.name),
          data = mapOf<String, Any>()
        )
      )
    }
  }
}