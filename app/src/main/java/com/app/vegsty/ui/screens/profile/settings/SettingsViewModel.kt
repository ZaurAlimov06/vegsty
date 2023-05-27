package com.app.vegsty.ui.screens.profile.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vegsty.data.local.ProfileLocal
import com.app.vegsty.ui.model.ExceptionHandler
import com.app.vegsty.ui.model.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
  private val profileLocal: ProfileLocal
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
    }
  }

  private fun getThemeState() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          themeState = profileLocal.getTheme()
        )
      }
    }
  }

  private fun onChangeTheme(isDark: Boolean) {
    viewModelScope.launch(ExceptionHandler.handler) {
      profileLocal.saveTheme(isDark)

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
}