package com.example.myapplication.ui.screens.homesearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.model.ExceptionHandler
import com.example.myapplication.ui.model.UiEvent
import com.example.myapplication.ui.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeSearchViewModel @Inject constructor(
  private val mainRepository: MainRepository
) : ViewModel() {
  private val _uiState = MutableStateFlow(HomeUiState())
  val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  init {
    getAllRecipes()
  }

  private fun getAllRecipes(){
    viewModelScope.launch(ExceptionHandler.handler){
      _uiState.update { currentState ->
        currentState.copy(
          recipeList = mainRepository.getAllRecipes()
        )
      }
    }
  }
}