package com.app.vegsty.ui.model

import com.app.vegsty.ui.route.NavigationType

sealed class UiEvent {
  data class Navigate<T>(val navigationType: NavigationType, val data: Map<String, T?>? = null) : UiEvent()
  data class ChangeTheme(val isDark: Boolean) : UiEvent()
}