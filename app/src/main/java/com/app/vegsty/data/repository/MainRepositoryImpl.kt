package com.app.vegsty.data.repository

import com.app.vegsty.data.local.preferences.MainLocal
import com.app.vegsty.data.local.room.GoalDao
import com.app.vegsty.data.local.room.RecipeDao
import com.app.vegsty.data.remote.dto.Goal
import com.app.vegsty.data.remote.dto.Recipe
import com.app.vegsty.data.remote.dto.Restaurant
import com.app.vegsty.ui.model.Response
import com.app.vegsty.ui.repository.MainRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class MainRepositoryImpl(
  private val firebaseAuth: FirebaseAuth,
  private val databaseReference: DatabaseReference,
  private val mainLocal: MainLocal,
  private val goalDao: GoalDao,
  private val recipeDao: RecipeDao
) : MainRepository {
  override suspend fun loginUser(email: String, password: String): Response<AuthResult> {
    return try {
      val response = firebaseAuth.signInWithEmailAndPassword(email, password).await()

      Response.Success(response)
    } catch (e: Exception) {
      Response.Fail(e)
    }
  }

  override suspend fun registerUser(userName: String, email: String, password: String): Response<AuthResult> {
    return try {
      val response = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
      response.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(userName).build())?.await()

      Response.Success(response)
    } catch (e: Exception) {
      Response.Fail(e)
    }
  }

  override suspend fun updateUser(userName: String): Response<Unit> {
    return try {
      val user = firebaseAuth.currentUser
      user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(userName).build())?.await()

      mainLocal.saveUsername(user?.displayName)

      Response.Success(Unit)
    } catch (e: Exception) {
      Response.Fail(e)
    }
  }

  override suspend fun getAllRecipes(): Response<List<Recipe>> {
    return try {
      val response = databaseReference.child("recipes").get().await()

      val responseList = mutableListOf<Recipe>()

      if (response.exists()) {
        for (recipeSnapshot in response.children) {
          val recipe = recipeSnapshot.getValue(Recipe::class.java)
          recipe?.let {
            responseList.add(recipe)
          }
        }
      }
      Response.Success(responseList)
    } catch (e: Exception) {
      Response.Fail(e)
    }
  }

  override suspend fun searchRecipes(searchText: String): Response<List<Recipe>> {
    return try {
      val response = databaseReference.child("recipes").get().await()

      val responseList = mutableListOf<Recipe>()

      if (response.exists()) {
        for (recipeSnapshot in response.children) {
          val recipe = recipeSnapshot.getValue(Recipe::class.java)
          recipe?.let {
            responseList.add(recipe)
          }
        }
      }
      Response.Success(responseList)
    } catch (e: Exception) {
      Response.Fail(e)
    }
  }

  override suspend fun insertRecipe(recipe: Recipe): Response<Unit> {
    return try {
      databaseReference.child("recipes").push().apply {
        key?.let {
          recipe.id = it
          setValue(recipe).await()
        }
      }

      Response.Success(Unit)
    } catch (e: Exception) {
      Response.Fail(e)
    }
  }

  override suspend fun insertRestaurant(restaurant: Restaurant): Response<Unit> {
    return try {
      databaseReference.child("restaurants").push().apply {
        key?.let {
          restaurant.id = it
          setValue(restaurant).await()
        }
      }

      Response.Success(Unit)
    } catch (e: Exception) {
      Response.Fail(e)
    }
  }

  override suspend fun logoutUser(): Response<Unit> {
    return try {
      firebaseAuth.signOut()

      mainLocal.deleteUsername()
      mainLocal.deleteEmail()
      mainLocal.deletePassword()

      Response.Success(Unit)
    } catch (e: Exception) {
      Response.Fail(e)
    }
  }

  override suspend fun saveFavoriteRecipe(recipe: Recipe): Response<Unit> {
    return try {
      recipeDao.insertFavoriteRecipe(recipe)

      Response.Success(Unit)
    } catch (e: Exception) {
      Response.Fail(e)
    }
  }

  override suspend fun getAllFavoriteRecipes(): Response<List<Recipe?>> {
    return try {
      Response.Success(recipeDao.getAllFavoriteRecipes())
    } catch (e: Exception) {
      Response.Fail(e)
    }
  }

  override suspend fun deleteFavoriteRecipe(recipe: Recipe): Response<Unit> {
    return try {
      recipeDao.deleteFavoriteRecipe(recipe)

      Response.Success(Unit)
    } catch (e: Exception) {
      Response.Fail(e)
    }
  }

  override suspend fun saveGoal(goal: Goal): Response<Unit> {
    return try {
      goalDao.insertGoal(goal)

      Response.Success(Unit)
    } catch (e: Exception) {
      Response.Fail(e)
    }
  }

  override suspend fun getAllGoals(): Response<List<Goal?>> {
    return try {
      Response.Success(goalDao.getAllGoals())
    } catch (e: Exception) {
      Response.Fail(e)
    }
  }

  override suspend fun deleteGoal(goal: Goal): Response<Unit> {
    return try {
      goalDao.deleteGoal(goal)

      Response.Success(Unit)
    } catch (e: Exception) {
      Response.Fail(e)
    }
  }

  override fun saveUsername(username: String?) {
    mainLocal.saveUsername(username)
  }

  override fun getUsername(): String {
    return mainLocal.getUsername()
  }

  override fun saveEmail(email: String) {
    mainLocal.saveEmail(email)
  }

  override fun getEmail(): String {
    return mainLocal.getEmail()
  }

  override fun savePassword(password: String) {
    mainLocal.savePassword(password)
  }

  override fun getPassword(): String {
    return mainLocal.getPassword()
  }

  override fun saveTheme(isDark: Boolean) {
    mainLocal.saveTheme(isDark)
  }

  override fun getTheme(): Boolean {
    return mainLocal.getTheme()
  }

  override fun saveOnboardState() {
    mainLocal.saveOnboardState()
  }

  override fun containOnboardState(): Boolean {
    return mainLocal.containOnboardState()
  }

  override fun containsLoginInfo(): Boolean {
    return mainLocal.containsLoginInfo()
  }
}