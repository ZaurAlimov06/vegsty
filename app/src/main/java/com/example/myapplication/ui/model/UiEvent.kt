package com.example.myapplication.ui.model

import com.example.myapplication.ui.route.NavigationType

sealed class UiEvent {
  data class Navigate<T>(val navigationType: NavigationType, val data: Map<String, T?>? = null) :
    UiEvent()
}