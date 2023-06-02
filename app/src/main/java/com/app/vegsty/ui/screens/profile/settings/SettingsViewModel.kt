package com.app.vegsty.ui.screens.profile.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vegsty.data.local.MainLocal
import com.app.vegsty.ui.model.ExceptionHandler
import com.app.vegsty.ui.model.UiEvent
import com.app.vegsty.ui.route.NavigationType
import com.app.vegsty.ui.route.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
  private val mainLocal: MainLocal
) : ViewModel() {
  private val _uiState = MutableStateFlow(SettingsUiState())
  val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  init {
    getThemeState()
  }

  fun onEvent(settingsUiEvent: SettingsUiEvent) {
    when (settingsUiEvent) {
      is SettingsUiEvent.OnChangeTheme -> {
        onChangeTheme(settingsUiEvent.isDark)
      }
      is SettingsUiEvent.OnSettingsClick -> {
        onSettingsClick()
      }
    }
  }

  private fun getThemeState() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          themeState = mainLocal.getTheme()
        )
      }
    }
  }

  private fun onChangeTheme(isDark: Boolean) {
    viewModelScope.launch(ExceptionHandler.handler) {
      mainLocal.saveTheme(isDark)

      _uiEvent.send(
        UiEvent.ChangeTheme(
          isDark
        )
      )

      _uiState.update { currentState ->
        currentState.copy(
          themeState = isDark
        )
      }
    }
  }

  private fun onSettingsClick() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(
        UiEvent.Navigate(
          navigationType = NavigationType.Navigate(Route.SCREEN_DATA_INSERT.name),
          data = mapOf<String, Any>()
        )
      )
    }
  }
}