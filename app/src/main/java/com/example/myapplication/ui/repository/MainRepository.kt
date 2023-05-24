package com.example.myapplication.ui.repository

import com.example.myapplication.data.remote.dto.Recipe

interface MainRepository {
  suspend fun getAllRecipes(): List<Recipe>
  suspend fun searchRecipes(searchText: String): List<Recipe>
}