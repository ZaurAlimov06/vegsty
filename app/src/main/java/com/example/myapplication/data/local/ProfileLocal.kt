package com.example.myapplication.data.local

interface ProfileLocal {
  fun saveTheme(isDark: Boolean)
  fun getTheme(): Boolean
  fun saveUsername(username: String)
  fun getUsername(): String
}