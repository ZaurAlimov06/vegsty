package com.app.vegsty.data.repository

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
  private val databaseReference: DatabaseReference
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
          setValue(recipe)
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
          setValue(restaurant)
        }
      }

      Response.Success(Unit)
    } catch (e: Exception) {
      Response.Fail(e)
    }
  }
}