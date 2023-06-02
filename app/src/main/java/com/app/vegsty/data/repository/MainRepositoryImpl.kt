package com.app.vegsty.data.repository

import com.app.vegsty.data.remote.dto.Recipe
import com.app.vegsty.ui.model.Response
import com.app.vegsty.ui.repository.MainRepository
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class MainRepositoryImpl(
  private val databaseReference: DatabaseReference
) : MainRepository {

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
}