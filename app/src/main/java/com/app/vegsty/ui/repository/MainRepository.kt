package com.app.vegsty.ui.repository

import com.app.vegsty.data.remote.dto.Recipe

interface MainRepository {
  suspend fun getAllRecipes(): List<Recipe>
  suspend fun searchRecipes(searchText: String): List<Recipe>
}