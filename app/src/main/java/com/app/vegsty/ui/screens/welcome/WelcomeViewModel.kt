package com.app.vegsty.ui.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vegsty.ui.model.ExceptionHandler
import com.app.vegsty.ui.model.UiEvent
import com.app.vegsty.ui.route.NavigationType
import com.app.vegsty.ui.route.Route
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
  private val firebaseAuth: FirebaseAuth
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
        onRegisterClick(welcomeScreenUiEvent.email, welcomeScreenUiEvent.password)
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
      is WelcomeScreenUiEvent.OnUpdateRegisterEmail -> {
        onUpdateRegisterEmail(welcomeScreenUiEvent.email)
      }
      is WelcomeScreenUiEvent.OnUpdateRegisterPassword -> {
        onUpdateRegisterPassword((welcomeScreenUiEvent.password))
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

  private fun onRegisterClick(
    email: String,
    password: String
  ) {
    viewModelScope.launch(ExceptionHandler.handler) {
      firebaseAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
          if (task.isSuccessful) {
//            _uiEvent.send(
//              UiEvent.Navigate(
//                navigationType = NavigationType.ClearBackStackNavigate(Route.SCREEN_SEARCH.name),
//                data = mapOf<String, Any>()
//              )
//            )
          } else {
            println("<<<<<<<<<<fail")
          }
        }
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

  private fun onUpdateRegisterEmail(email: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          registerEmail = email
        )
      }
    }
  }

  private fun onUpdateRegisterPassword(password: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          registerPassword = password
        )
      }
    }
  }
}