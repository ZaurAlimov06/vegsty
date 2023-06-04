package com.app.vegsty.ui.screens.goals.insert

sealed class GoalInsertUiEvent {
  object OnInsertGoal : GoalInsertUiEvent()
  data class OnUpdateGoalTitle(val title: String) : GoalInsertUiEvent()
  data class OnUpdateGoalProtein(val protein: String) : GoalInsertUiEvent()
  data class OnUpdateGoalCalorie(val calorie: String) : GoalInsertUiEvent()
  data class OnUpdateGoalFat(val fat: String) : GoalInsertUiEvent()
  data class OnUpdateGoalCarbs(val carbs: String) : GoalInsertUiEvent()
  data class OnUpdateGoalDate(val date: String) : GoalInsertUiEvent()
}
