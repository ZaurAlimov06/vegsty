package com.app.vegsty.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vegsty.data.remote.dto.Recipe
import com.app.vegsty.ui.model.ExceptionHandler
import com.app.vegsty.ui.model.Response
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
class FavoritesViewModel @Inject constructor(
  private val mainRepository: MainRepository
) : ViewModel() {
  private val _uiState = MutableStateFlow(FavoritesUiState())
  val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onEvent(favoritesUiEvent: FavoritesUiEvent) {
    when (favoritesUiEvent) {
      is FavoritesUiEvent.GetAllFavorites -> {
        getFavoriteRecipes()
      }
      is FavoritesUiEvent.OnFavoriteRecipeClick -> {
        onFavoriteRecipeClick(favoritesUiEvent.recipe)
      }
    }
  }

  private fun getFavoriteRecipes() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(UiEvent.ShowLoading)

      when (val result = mainRepository.getAllFavoriteRecipes()) {
        is Response.Fail -> {
          _uiEvent.send(UiEvent.ShowShortToast(result.exception.message))
          _uiEvent.send(UiEvent.HideLoading)
        }
        is Response.Success -> {
          updateRecipes(result.result)
          _uiEvent.send(UiEvent.HideLoading)
        }
      }
    }
  }

  private fun updateRecipes(recipes: List<Recipe?>) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update {
        FavoritesUiState(recipes)
      }
    }
  }

  private fun onFavoriteRecipeClick(recipe: Recipe?) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(
        UiEvent.Navigate(
          navigationType = NavigationType.Navigate(Route.SCREEN_HOME_RECIPE_DETAIL.name),
          data = mapOf(
            RouteArgument.ARG_HOME_RECIPE_DETAIL.name to recipe,
            RouteArgument.ARG_DETAIL_IS_FROM_HOME.name to false
          )
        )
      )
    }
  }
}