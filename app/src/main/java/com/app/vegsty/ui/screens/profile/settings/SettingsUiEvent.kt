package com.app.vegsty.ui.screens.profile.settings

sealed class SettingsUiEvent {
  data class OnChangeTheme(val isDark: Boolean) : SettingsUiEvent()
  object OnSettingsClick : SettingsUiEvent()
}