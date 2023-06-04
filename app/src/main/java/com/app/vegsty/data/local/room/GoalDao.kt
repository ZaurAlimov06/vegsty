package com.app.vegsty.data.local.room

import androidx.room.*
import com.app.vegsty.data.remote.dto.Goal

@Dao
interface GoalDao {
  @Query("SELECT * FROM goal")
  suspend fun getAllGoals(): List<Goal?>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertGoal(goal: Goal)

  @Delete
  suspend fun deleteGoal(goal: Goal)
}