package com.example.myapplication.ui.screens.homesearch

import com.example.myapplication.data.remote.dto.Recipe

data class HomeUiState(
  val recipeList: List<Recipe> = listOf()
)