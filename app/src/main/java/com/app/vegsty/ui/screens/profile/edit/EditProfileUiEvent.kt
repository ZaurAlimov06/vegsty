package com.app.vegsty.ui.screens.profile.edit

sealed class EditProfileUiEvent {
  data class OnUpdateUsername(val username: String) : EditProfileUiEvent()
  object OnUpdateRemoteUsername : EditProfileUiEvent()
}