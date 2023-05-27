package com.app.vegsty.data.local

interface ProfileLocal {
  fun saveTheme(isDark: Boolean)
  fun getTheme(): Boolean
  fun saveUsername(username: String)
  fun getUsername(): String
}