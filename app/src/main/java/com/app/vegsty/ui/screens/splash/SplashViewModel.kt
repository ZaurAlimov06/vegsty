package com.app.vegsty.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vegsty.data.local.MainLocal
import com.app.vegsty.ui.model.ExceptionHandler
import com.app.vegsty.ui.model.UiEvent
import com.app.vegsty.ui.route.NavigationType
import com.app.vegsty.ui.route.Route
import com.app.vegsty.ui.route.RouteArgument
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
  private val firebaseAuth: FirebaseAuth,
  private val mainLocal: MainLocal
) : ViewModel() {
  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  init {
    startSplashTimer()
  }

  private fun startSplashTimer() {
    viewModelScope.launch(ExceptionHandler.handler) {
      if (mainLocal.containOnboardState()) {
        if (mainLocal.containsLoginInfo()) {
          firebaseAuth.signInWithEmailAndPassword(mainLocal.getEmail(), mainLocal.getPassword()).await()

          _uiEvent.send(
            UiEvent.Navigate(
              navigationType = NavigationType.ClearBackStackNavigate(Route.SCREEN_SEARCH.name),
              data = mapOf<String, Any>()
            )
          )
        } else {
          _uiEvent.send(
            UiEvent.Navigate(
              navigationType = NavigationType.ClearBackStackNavigate(Route.SCREEN_WELCOME.name),
              data = mapOf(
                RouteArgument.ARG_WELCOME_IS_LOGIN_SCREEN.name to true
              )
            )
          )
        }
      } else {
        mainLocal.saveOnboardState()

        _uiEvent.send(
          UiEvent.Navigate(
            navigationType = NavigationType.ClearBackStackNavigate(Route.SCREEN_ONBOARD.name),
            data = mapOf<String, Any>()
          )
        )
      }

      onChangeTheme(mainLocal.getTheme())
    }
  }

  private fun onChangeTheme(isDark: Boolean) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(
        UiEvent.ChangeTheme(isDark)
      )
    }
  }
}