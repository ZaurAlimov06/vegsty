package com.app.vegsty.ui.screens.welcome

data class WelcomeUiState(
  val registerEmail: String = "",
  val registerPassword: String = "",
  val isLoginPassVisible: Boolean = false,
  val isRegisterPassVisible: Boolean = false,
  val isRegisterAgainPassVisible: Boolean = false
)