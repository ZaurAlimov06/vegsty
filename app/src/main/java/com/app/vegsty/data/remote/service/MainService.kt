package com.app.vegsty.data.remote.service

import com.app.vegsty.data.remote.dto.Recipe
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MainService {
  @GET("/all-recipes")
  suspend fun getAllRecipes(): List<Recipe>

  @POST("/search-recipes")
  suspend fun searchRecipes(
    @Body searchText: String
  ): List<Recipe>
}