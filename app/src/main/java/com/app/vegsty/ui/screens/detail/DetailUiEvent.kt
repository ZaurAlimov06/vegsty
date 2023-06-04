package com.app.vegsty.ui.screens.detail

import com.app.vegsty.data.remote.dto.Recipe

sealed class DetailUiEvent {
  data class OnAddToFavorites(val recipe: Recipe?) : DetailUiEvent()
}