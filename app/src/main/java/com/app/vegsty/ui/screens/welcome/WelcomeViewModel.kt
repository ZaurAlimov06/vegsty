package com.app.vegsty.ui.screens.welcome

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vegsty.data.local.MainLocal
import com.app.vegsty.ui.model.ExceptionHandler
import com.app.vegsty.ui.model.Response
import com.app.vegsty.ui.model.UiEvent
import com.app.vegsty.ui.repository.MainRepository
import com.app.vegsty.ui.route.NavigationType
import com.app.vegsty.ui.route.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
  private val mainRepository: MainRepository,
  private val mainLocal: MainLocal
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
      is WelcomeScreenUiEvent.OnUpdateRegisterEmail -> {
        onUpdateRegisterEmail(welcomeScreenUiEvent.email)
      }
      is WelcomeScreenUiEvent.OnUpdateRegisterPassword -> {
        onUpdateRegisterPassword((welcomeScreenUiEvent.password))
      }
      is WelcomeScreenUiEvent.OnUpdateRegisterPasswordAgain -> {
        onUpdateRegisterPasswordAgain((welcomeScreenUiEvent.passwordAgain))
      }
      is WelcomeScreenUiEvent.OnUpdateRegisterUsername -> {
        onUpdateRegisterUsername(welcomeScreenUiEvent.username)
      }
      is WelcomeScreenUiEvent.OnUpdateLoginEmail -> {
        onUpdateLoginEmail(welcomeScreenUiEvent.email)
      }
      is WelcomeScreenUiEvent.OnUpdateLoginPassword -> {
        onUpdateLoginPassword(welcomeScreenUiEvent.password)
      }
    }
  }

  private fun onLoginClick() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(UiEvent.ShowLoading)

      when (val result = mainRepository.loginUser(
        _uiState.value.loginEmail,
        _uiState.value.loginPassword
      )) {
        is Response.Fail -> {
          _uiEvent.send(UiEvent.ShowShortToast(result.exception.message))
          _uiEvent.send(UiEvent.HideLoading)
        }
        is Response.Success -> {
          mainLocal.saveUsername(result.result.user?.displayName)
          mainLocal.saveEmail(_uiState.value.loginEmail)
          mainLocal.savePassword(_uiState.value.loginPassword)

          _uiEvent.send(
            UiEvent.Navigate(
              navigationType = NavigationType.ClearBackStackNavigate(Route.SCREEN_SEARCH.name),
              data = mapOf<String, Any>()
            )
          )
          clearInputs()
          _uiEvent.send(UiEvent.HideLoading)
        }
      }
    }
  }

  private fun onRegisterClick() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(UiEvent.ShowLoading)

      when (val result = mainRepository.registerUser(
        _uiState.value.registerUsername,
        _uiState.value.registerEmail,
        _uiState.value.registerPassword
      )) {
        is Response.Fail -> {
          _uiEvent.send(UiEvent.ShowShortToast(result.exception.message))
          _uiEvent.send(UiEvent.HideLoading)
        }
        is Response.Success -> {
          clearInputs()
          _uiEvent.send(
            UiEvent.ShowLongToast("${result.result.user?.displayName} successfully registered.")
          )
          _uiEvent.send(UiEvent.NavigateOnScreen(true))
          _uiEvent.send(UiEvent.HideLoading)
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
      onUpdateRegisterEnabled()
    }
  }

  private fun onUpdateRegisterUsername(username: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          registerUsername = username
        )
      }
      onUpdateRegisterEnabled()
    }
  }

  private fun onUpdateRegisterPassword(password: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          registerPassword = password
        )
      }
      onUpdateRegisterEnabled()
    }
  }

  private fun onUpdateRegisterPasswordAgain(passwordAgain: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          registerPasswordAgain = passwordAgain
        )
      }
      onUpdateRegisterEnabled()
    }
  }

  private fun onUpdateLoginEmail(email: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          loginEmail = email
        )
      }
      onUpdateLoginEnabled()
    }
  }

  private fun onUpdateLoginPassword(password: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          loginPassword = password
        )
      }
      onUpdateLoginEnabled()
    }
  }

  private fun onUpdateRegisterEnabled() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          isRegisterButtonEnabled = _uiState.value.registerUsername.isNotEmpty() &&
            _uiState.value.registerEmail.isNotEmpty() &&
            Patterns.EMAIL_ADDRESS.matcher(_uiState.value.registerEmail).matches() &&
            _uiState.value.registerPassword.isNotEmpty() &&
            _uiState.value.registerPasswordAgain.isNotEmpty() &&
            _uiState.value.registerPassword == _uiState.value.registerPasswordAgain
        )
      }
    }
  }

  private fun onUpdateLoginEnabled() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          isLoginButtonEnabled = _uiState.value.loginEmail.isNotEmpty() &&
            Patterns.EMAIL_ADDRESS.matcher(_uiState.value.loginEmail).matches() &&
            _uiState.value.loginPassword.isNotEmpty()
        )
      }
    }
  }

  private fun clearInputs() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update {
        WelcomeUiState()
      }
    }
  }
}