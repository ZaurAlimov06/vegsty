package com.app.vegsty.ui.screens.homesearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vegsty.data.remote.dto.Recipe
import com.app.vegsty.ui.model.ExceptionHandler
import com.app.vegsty.ui.model.UiEvent
import com.app.vegsty.ui.repository.MainRepository
import com.app.vegsty.ui.route.NavigationType
import com.app.vegsty.ui.route.Route
import com.app.vegsty.ui.route.RouteArgument
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

  fun onEvent(homeUiEvent: HomeUiEvent){
    when(homeUiEvent){
      is HomeUiEvent.OnRecipeClick -> {
        navigateToDetail(homeUiEvent.recipe)
      }
    }
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

  private fun navigateToDetail(recipe: Recipe){
    viewModelScope.launch(ExceptionHandler.handler){
      _uiEvent.send(
        UiEvent.Navigate(
          navigationType = NavigationType.Navigate(Route.SCREEN_HOME_RECIPE_DETAIL.name),
          data = mapOf(
            RouteArgument.ARG_HOME_RECIPE_DETAIL.name to recipe
          )
        )
      )
    }
  }
}