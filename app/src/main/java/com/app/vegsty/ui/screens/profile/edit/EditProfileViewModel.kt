package com.app.vegsty.ui.screens.profile.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vegsty.data.local.MainLocal
import com.app.vegsty.ui.model.ExceptionHandler
import com.app.vegsty.ui.model.UiEvent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
  private val firebaseAuth: FirebaseAuth,
  private val mainLocal: MainLocal
) : ViewModel() {
  private val _uiState = MutableStateFlow(EditProfileUiState())
  val uiState: StateFlow<EditProfileUiState> = _uiState.asStateFlow()

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  init {
    getUsername()
  }

  fun onEvent(editProfileUiEvent: EditProfileUiEvent) {
    when (editProfileUiEvent) {
      is EditProfileUiEvent.OnUpdateRemoteUsername -> {
        onUpdateRemoteUsername()
      }
      is EditProfileUiEvent.OnUpdateUsername -> {
        onUpdateUsername(editProfileUiEvent.username)
      }
    }
  }

  private fun getUsername() {
    viewModelScope.launch(ExceptionHandler.handler) {
      onUpdateUsername(mainLocal.getUsername())
    }
  }

  private fun onUpdateUsername(username: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          userName = username
        )
      }
    }
  }

  private fun onUpdateRemoteUsername() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(UiEvent.ShowLoading)

      try {
        val user = firebaseAuth.currentUser
        user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(_uiState.value.userName).build())?.await()

        mainLocal.saveUsername(user?.displayName)

        _uiEvent.send(
          UiEvent.ShowLongToast("Username successfully updated!")
        )
        _uiEvent.send(UiEvent.HideLoading)
      } catch (e: Exception) {
        _uiEvent.send(
          UiEvent.ShowShortToast(e.message)
        )

        _uiEvent.send(UiEvent.HideLoading)
      }
    }
  }
}