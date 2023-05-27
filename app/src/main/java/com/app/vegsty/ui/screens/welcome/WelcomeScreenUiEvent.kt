package com.app.vegsty.ui.screens.welcome

sealed class WelcomeScreenUiEvent {
  object OnLoginClick : WelcomeScreenUiEvent()
  data class OnRegisterClick(val email: String, val password: String) : WelcomeScreenUiEvent()
  data class OnUpdateLoginPassVisibility(val state: Boolean) : WelcomeScreenUiEvent()
  data class OnUpdateRegisterPassVisibility(val state: Boolean) : WelcomeScreenUiEvent()
  data class OnUpdateRegisterAgainPassVisibility(val state: Boolean) : WelcomeScreenUiEvent()
  data class OnUpdateRegisterEmail(val email: String) : WelcomeScreenUiEvent()
  data class OnUpdateRegisterPassword(val password: String) : WelcomeScreenUiEvent()
}