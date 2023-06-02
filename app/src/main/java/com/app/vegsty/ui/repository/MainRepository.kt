package com.app.vegsty.ui.repository

import com.app.vegsty.data.remote.dto.Recipe
import com.app.vegsty.ui.model.Response

interface MainRepository {
  suspend fun getAllRecipes(): Response<List<Recipe>>
  suspend fun searchRecipes(searchText: String): Response<List<Recipe>>
}