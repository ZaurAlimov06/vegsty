package com.example.myapplication.ui.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.model.ExceptionHandler
import com.example.myapplication.ui.model.UiEvent
import com.example.myapplication.ui.route.NavigationType
import com.example.myapplication.ui.route.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
) : ViewModel() {
  private val _uiState = MutableStateFlow(WelcomeUiState())
  val uiState: StateFlow<WelcomeUiState> = _uiState.asStateFlow()

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onEvent(welcomeScreenUiEvent: WelcomeScreenUiEvent) {
    when (welcomeScreenUiEvent) {
      is WelcomeScreenUiEvent.OnLoginClick -> {
        onLoginClick()
      }
      is WelcomeScreenUiEvent.OnRegisterClick -> {
        onRegisterClick()
      }
      is WelcomeScreenUiEvent.OnUpdateLoginPassVisibility -> {
        updateLoginPassVisibility(welcomeScreenUiEvent.state)
      }
      is WelcomeScreenUiEvent.OnUpdateRegisterAgainPassVisibility -> {
        onUpdateRegisterAgainPassVisibility(welcomeScreenUiEvent.state)
      }
      is WelcomeScreenUiEvent.OnUpdateRegisterPassVisibility -> {
        onUpdateRegisterPassVisibility(welcomeScreenUiEvent.state)
      }
    }
  }

  private fun onLoginClick() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(
        UiEvent.Navigate(
          navigationType = NavigationType.ClearBackStackNavigate(Route.SCREEN_SEARCH.name),
          data = mapOf<String, Any>()
        )
      )
    }
  }

  private fun onRegisterClick() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(
        UiEvent.Navigate(
          navigationType = NavigationType.ClearBackStackNavigate(Route.SCREEN_SEARCH.name),
          data = mapOf<String, Any>()
        )
      )
    }
  }

  private fun updateLoginPassVisibility(state: Boolean) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          isLoginPassVisible = state
        )
      }
    }
  }

  private fun onUpdateRegisterPassVisibility(state: Boolean) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          isRegisterPassVisible = state
        )
      }
    }
  }

  private fun onUpdateRegisterAgainPassVisibility(state: Boolean) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          isRegisterAgainPassVisible = state
        )
      }
    }
  }
}