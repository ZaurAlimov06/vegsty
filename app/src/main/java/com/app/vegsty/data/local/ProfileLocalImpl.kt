package com.app.vegsty.data.local

import com.app.vegsty.ui.repository.PreferenceHelper

class ProfileLocalImpl(
  private val preferenceHelper: PreferenceHelper
) : ProfileLocal {
  override fun saveTheme(isDark: Boolean) {
    preferenceHelper.saveBoolean(KEY_THEME, isDark)
  }

  override fun getTheme(): Boolean {
    return preferenceHelper.getBoolean(KEY_THEME)
  }

  override fun saveUsername(username: String) {
    preferenceHelper.saveString(KEY_USERNAME, username)
  }

  override fun getUsername(): String {
    return preferenceHelper.getString(KEY_USERNAME)
  }

  companion object {
    const val KEY_THEME = "profile.theme"
    const val KEY_USERNAME = "profile.username"
  }
}