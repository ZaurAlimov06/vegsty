package com.app.vegsty.ui.repository

import com.app.vegsty.data.remote.dto.Recipe
import com.app.vegsty.data.remote.dto.Restaurant
import com.app.vegsty.ui.model.Response
import com.google.firebase.auth.AuthResult

interface MainRepository {
  suspend fun loginUser(email: String, password: String): Response<AuthResult>
  suspend fun registerUser(userName: String, email: String, password: String): Response<AuthResult>
  suspend fun getAllRecipes(): Response<List<Recipe>>
  suspend fun searchRecipes(searchText: String): Response<List<Recipe>>
  suspend fun insertRecipe(recipe: Recipe): Response<Unit>
  suspend fun insertRestaurant(restaurant: Restaurant): Response<Unit>
}