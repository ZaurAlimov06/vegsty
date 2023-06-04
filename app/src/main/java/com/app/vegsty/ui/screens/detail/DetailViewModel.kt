package com.app.vegsty.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vegsty.data.remote.dto.Recipe
import com.app.vegsty.ui.model.ExceptionHandler
import com.app.vegsty.ui.model.Response
import com.app.vegsty.ui.model.UiEvent
import com.app.vegsty.ui.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
  private val mainRepository: MainRepository
) : ViewModel() {
  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onEvent(detailUiEvent: DetailUiEvent) {
    when (detailUiEvent) {
      is DetailUiEvent.OnAddToFavorites -> {
        onAddToFavorites(detailUiEvent.recipe)
      }
    }
  }

  private fun onAddToFavorites(recipe: Recipe?) {
    viewModelScope.launch(ExceptionHandler.handler) {

      _uiEvent.send(UiEvent.ShowLoading)
      val result = recipe?.let { mainRepository.saveFavoriteRecipe(it) }

      when (result) {
        is Response.Fail -> {
          _uiEvent.send(UiEvent.ShowShortToast(result.exception.message))
          _uiEvent.send(UiEvent.HideLoading)
        }
        is Response.Success -> {

          _uiEvent.send(
            UiEvent.ShowShortToast("Recipe added to favorites!")
          )

          _uiEvent.send(UiEvent.HideLoading)
        }
        else -> {
          println()
        }
      }
    }

  }
}