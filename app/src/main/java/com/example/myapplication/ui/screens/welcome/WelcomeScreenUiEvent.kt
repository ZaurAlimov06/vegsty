package com.example.myapplication.ui.screens.welcome

sealed class WelcomeScreenUiEvent {
  object OnLoginClick : WelcomeScreenUiEvent()
  object OnRegisterClick : WelcomeScreenUiEvent()
}