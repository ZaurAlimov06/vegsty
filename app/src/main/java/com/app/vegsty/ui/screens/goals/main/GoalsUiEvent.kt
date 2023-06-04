package com.app.vegsty.ui.screens.goals.main

import com.app.vegsty.data.remote.dto.Goal

sealed class GoalsUiEvent {
  object GetAllGoals : GoalsUiEvent()
  object OnDisplayProgressClick : GoalsUiEvent()
  object OnAddGoalClick : GoalsUiEvent()
  data class OnGoalClick(val goal: Goal?) : GoalsUiEvent()
}
