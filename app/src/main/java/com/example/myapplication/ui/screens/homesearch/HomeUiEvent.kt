package com.example.myapplication.ui.screens.homesearch

import com.example.myapplication.data.remote.dto.Recipe

sealed class HomeUiEvent{
  data class OnRecipeClick(val recipe: Recipe) : HomeUiEvent()
}
