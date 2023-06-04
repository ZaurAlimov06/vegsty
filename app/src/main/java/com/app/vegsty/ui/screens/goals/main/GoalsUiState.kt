package com.app.vegsty.ui.screens.goals.main

import com.app.vegsty.data.remote.dto.Goal

data class GoalsUiState(
  val goals: List<Goal?> = listOf()
)
