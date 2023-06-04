package com.app.vegsty.data.local.room

import androidx.room.*
import com.app.vegsty.data.remote.dto.Goal
import com.app.vegsty.data.remote.dto.Recipe

@Dao
interface MainDao {

  @Query("SELECT * FROM recipe")
  suspend fun getAllFavoriteRecipes(): List<Recipe?>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertFavoriteRecipe(recipe: Recipe)

  @Delete
  suspend fun deleteFavoriteRecipe(recipe: Recipe)

  @Query("SELECT * FROM goal")
  suspend fun getAllGoals(): List<Goal?>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertGoal(goal: Goal)

  @Delete
  suspend fun deleteGoal(goal: Goal)
}