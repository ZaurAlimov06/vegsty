package com.example.myapplication.ui.screens.favorites

import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.model.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
) : ViewModel() {
  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

}