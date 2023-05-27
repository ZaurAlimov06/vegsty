package com.app.vegsty.ui.screens.profile.main

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
class ProfileViewModel @Inject constructor(
) : ViewModel() {
  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onEvent(profileUiEvent: ProfileUiEvent) {
    when (profileUiEvent) {
      ProfileUiEvent.OnAboutUsClick -> {
        onAboutUsClick()
      }
      is ProfileUiEvent.OnEditProfileClick -> {
        onEditProfileClick()
      }
      is ProfileUiEvent.OnLogOutClick -> {
        onLogOutClick()
      }
      is ProfileUiEvent.OnSettingsClick -> {
        onSettingsClick()
      }
      is ProfileUiEvent.OnTermsClick -> {
        onTermsClick()
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

  private fun onEditProfileClick() {
    viewModelScope.launch(ExceptionHandler.handler) {

    }
  }

  private fun onLogOutClick() {
    viewModelScope.launch(ExceptionHandler.handler) {

    }
  }

  private fun onSettingsClick() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(
        UiEvent.Navigate(
          navigationType = NavigationType.Navigate(Route.SCREEN_SETTINGS.name),
          data = mapOf<String, Any>()
        )
      )
    }
  }

  private fun onTermsClick() {
    viewModelScope.launch(ExceptionHandler.handler) {

    }
  }
}