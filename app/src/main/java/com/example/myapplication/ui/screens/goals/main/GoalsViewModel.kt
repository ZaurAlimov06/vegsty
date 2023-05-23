package com.example.myapplication.ui.screens.goals.main

import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.model.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject constructor(
) : ViewModel() {
  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onEvent() {

  }
}