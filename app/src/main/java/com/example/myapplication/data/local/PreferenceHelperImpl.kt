package com.example.myapplication.data.local

import android.content.SharedPreferences
import com.example.myapplication.ui.repository.PreferenceHelper

class PreferenceHelperImpl(
  private val encryptedSharedPreferences: SharedPreferences
) : PreferenceHelper {

  override fun saveString(key: String, value: String) {
    encryptedSharedPreferences.edit().putString(key, value).apply()
  }

  override fun getString(key: String): String {
    return getString(key, null)
  }

  override fun getString(key: String, defaultValue: String?): String {
    return encryptedSharedPreferences.getString(key, defaultValue) ?: ""
  }

  override fun saveBoolean(key: String, value: Boolean) {
    encryptedSharedPreferences.edit().putBoolean(key, value).apply()
  }

  override fun getBoolean(key: String): Boolean {
    return encryptedSharedPreferences.getBoolean(key, false)
  }

  override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
    return encryptedSharedPreferences.getBoolean(key, defaultValue)
  }

  override fun clear(key: String): Boolean {
    return encryptedSharedPreferences.edit().remove(key).commit()
  }
}