package com.example.myapplication.ui.screens.welcome

sealed class WelcomeScreenUiEvent {
  object OnLoginClick : WelcomeScreenUiEvent()
  object OnRegisterClick : WelcomeScreenUiEvent()
  data class OnUpdateLoginPassVisibility(val state: Boolean) : WelcomeScreenUiEvent()
  data class OnUpdateRegisterPassVisibility(val state: Boolean) : WelcomeScreenUiEvent()
  data class OnUpdateRegisterAgainPassVisibility(val state: Boolean) : WelcomeScreenUiEvent()
}