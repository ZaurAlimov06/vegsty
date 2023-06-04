package com.app.vegsty.ui.screens.goals.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vegsty.data.remote.dto.Goal
import com.app.vegsty.ui.model.ExceptionHandler
import com.app.vegsty.ui.model.Response
import com.app.vegsty.ui.model.UiEvent
import com.app.vegsty.ui.repository.MainRepository
import com.app.vegsty.ui.route.NavigationType
import com.app.vegsty.ui.route.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject constructor(
  private val mainRepository: MainRepository
) : ViewModel() {
  private val _uiState = MutableStateFlow(GoalsUiState())
  val uiState: StateFlow<GoalsUiState> = _uiState.asStateFlow()

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onEvent(goalsUiEvent: GoalsUiEvent) {
    when (goalsUiEvent) {
      is GoalsUiEvent.OnDisplayProgressClick -> {
        onDisplayProgressClick()
      }
      is GoalsUiEvent.GetAllGoals -> {
        getAllGoals()
      }
      is GoalsUiEvent.OnGoalClick -> {
        onGoalClick(goalsUiEvent.goal)
      }
      is GoalsUiEvent.OnAddGoalClick -> {
        onAddGoalClick()
      }
    }
  }

  private fun onDisplayProgressClick() {
    viewModelScope.launch(ExceptionHandler.handler) {

    }
  }

  private fun getAllGoals() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(UiEvent.ShowLoading)

      when (val result = mainRepository.getAllGoals()) {
        is Response.Fail -> {
          _uiEvent.send(UiEvent.ShowShortToast(result.exception.message))
          _uiEvent.send(UiEvent.HideLoading)
        }
        is Response.Success -> {
          updateGoals(result.result)
          _uiEvent.send(UiEvent.HideLoading)
        }
      }
    }
  }

  private fun updateGoals(goals: List<Goal?>) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update {
        GoalsUiState(goals)
      }
    }
  }

  private fun onGoalClick(goal: Goal?) {
    viewModelScope.launch(ExceptionHandler.handler) {

    }
  }

  private fun onAddGoalClick() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(
        UiEvent.Navigate(
          navigationType = NavigationType.Navigate(Route.SCREEN_GOAL_ADD.name),
          data = mapOf<String, Any>()
        )
      )
    }
  }
}