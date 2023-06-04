package com.app.vegsty.ui.screens.goals.insert

data class GoalInsertUiState(
  val goalTitle: String = "",
  val goalProtein: String = "",
  val goalCalorie: String = "",
  val goalFat: String = "",
  val goalCarbs: String = "",
  val goalDate: String = "",
  val isGoalButtonEnabled: Boolean = false,
)