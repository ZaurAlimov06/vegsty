package com.example.myapplication.ui.screens.profile.main

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
class ProfileViewModel @Inject constructor(
) : ViewModel() {
  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()
  fun onEvent(profileUiEvent: ProfileUiEvent) {
    when (profileUiEvent) {
      ProfileUiEvent.OnAboutUsClick -> {
        onAboutUsClick()
      }
    }
  }

  private fun onAboutUsClick() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(
        UiEvent.Navigate(
          navigationType = NavigationType.Navigate(Route.SCREEN_ABOUT_US.name),
          data = mapOf<String, Any>()
        )
      )
    }
  }
}