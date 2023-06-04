package com.app.vegsty.ui.repository

import com.app.vegsty.data.remote.dto.Goal
import com.app.vegsty.data.remote.dto.Recipe
import com.app.vegsty.data.remote.dto.Restaurant
import com.app.vegsty.ui.model.Response
import com.google.firebase.auth.AuthResult

interface MainRepository {
  suspend fun loginUser(email: String, password: String): Response<AuthResult>
  suspend fun registerUser(userName: String, email: String, password: String): Response<AuthResult>
  suspend fun updateUser(userName: String): Response<Unit>
  suspend fun getAllRecipes(): Response<List<Recipe>>
  suspend fun insertRecipe(recipe: Recipe): Response<Unit>
  suspend fun insertRestaurant(restaurant: Restaurant): Response<Unit>
  suspend fun getAllRestaurants(): Response<List<Restaurant>>
  suspend fun logoutUser(): Response<Unit>
  suspend fun sendRecipeStatistic(): Response<Unit>
  suspend fun sendGoalStatistic(): Response<Unit>

  suspend fun saveFavoriteRecipe(recipe: Recipe): Response<Unit>
  suspend fun getAllFavoriteRecipes(): Response<List<Recipe?>>
  suspend fun deleteFavoriteRecipe(recipe: Recipe): Response<Unit>
  suspend fun saveGoal(goal: Goal): Response<Unit>
  suspend fun getAllGoals(): Response<List<Goal?>>
  suspend fun deleteGoal(goal: Goal): Response<Unit>

  fun saveUsername(username: String?)
  fun getUsername(): String
  fun saveEmail(email: String)
  fun getEmail(): String
  fun savePassword(password: String)
  fun getPassword(): String
  fun saveTheme(isDark: Boolean)
  fun getTheme(): Boolean
  fun saveOnboardState()
  fun containOnboardState(): Boolean
  fun containsLoginInfo(): Boolean
}