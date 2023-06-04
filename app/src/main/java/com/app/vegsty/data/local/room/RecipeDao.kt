package com.app.vegsty.data.local.room

import androidx.room.*
import com.app.vegsty.data.remote.dto.Recipe

@Dao
interface RecipeDao {
  @Query("SELECT * FROM recipe")
  suspend fun getAllFavoriteRecipes(): List<Recipe?>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertFavoriteRecipe(recipe: Recipe)

  @Delete
  suspend fun deleteFavoriteRecipe(recipe: Recipe)
}