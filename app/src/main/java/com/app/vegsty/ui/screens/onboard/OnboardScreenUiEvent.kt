package com.app.vegsty.ui.screens.onboard

sealed class OnboardScreenUiEvent {
  object OnGetStartedClick : OnboardScreenUiEvent()
  object OnLogInClick : OnboardScreenUiEvent()
}