package com.example.myapplication.ui.screens.profile.main


sealed class ProfileUiEvent {
  object OnEditProfileClick : ProfileUiEvent()
  object OnSettingsClick : ProfileUiEvent()
  object OnAboutUsClick : ProfileUiEvent()
  object OnTermsClick : ProfileUiEvent()
  object OnLogOutClick : ProfileUiEvent()
}