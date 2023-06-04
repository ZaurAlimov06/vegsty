package com.app.vegsty.ui.screens.goals.insert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vegsty.data.remote.dto.Goal
import com.app.vegsty.ui.model.ExceptionHandler
import com.app.vegsty.ui.model.Response
import com.app.vegsty.ui.model.UiEvent
import com.app.vegsty.ui.repository.MainRepository
import com.app.vegsty.ui.route.NavigationType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalInsertViewModel @Inject constructor(
  private val mainRepository: MainRepository
) : ViewModel() {
  private val _uiState = MutableStateFlow(GoalInsertUiState())
  val uiState: StateFlow<GoalInsertUiState> = _uiState.asStateFlow()

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onEvent(goalInsertUiEvent: GoalInsertUiEvent) {
    when (goalInsertUiEvent) {
      is GoalInsertUiEvent.OnInsertGoal -> {
        onInsertGoal()
      }
      is GoalInsertUiEvent.OnUpdateGoalTitle -> {
        onUpdateTitle(goalInsertUiEvent.title)
      }
      is GoalInsertUiEvent.OnUpdateGoalCalorie -> {
        onUpdateCalorie(goalInsertUiEvent.calorie)
      }
      is GoalInsertUiEvent.OnUpdateGoalCarbs -> {
        onUpdateCarbs(goalInsertUiEvent.carbs)
      }
      is GoalInsertUiEvent.OnUpdateGoalFat -> {
        onUpdateFat(goalInsertUiEvent.fat)
      }
      is GoalInsertUiEvent.OnUpdateGoalProtein -> {
        onUpdateProtein(goalInsertUiEvent.protein)
      }
      is GoalInsertUiEvent.OnUpdateGoalDate -> {
        onUpdateDate(goalInsertUiEvent.date)
      }
    }
  }

  private fun onUpdateTitle(title: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          goalTitle = title
        )
      }
      onUpdateAddGoalEnabled()
    }
  }

  private fun onUpdateCalorie(calorie: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          goalCalorie = calorie
        )
      }
      onUpdateAddGoalEnabled()
    }
  }

  private fun onUpdateCarbs(carbs: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          goalCarbs = carbs
        )
      }
      onUpdateAddGoalEnabled()
    }
  }

  private fun onUpdateFat(fat: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          goalFat = fat
        )
      }
      onUpdateAddGoalEnabled()
    }
  }

  private fun onUpdateProtein(protein: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          goalProtein = protein
        )
      }
      onUpdateAddGoalEnabled()
    }
  }

  private fun onUpdateDate(date: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          goalDate = date
        )
      }
      onUpdateAddGoalEnabled()
    }
  }

  private fun onInsertGoal() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(UiEvent.ShowLoading)
      when (val result = mainRepository.saveGoal(
        Goal(
          title = _uiState.value.goalTitle,
          calorie = _uiState.value.goalCalorie,
          carbs = _uiState.value.goalCarbs,
          fat = _uiState.value.goalFat,
          protein = _uiState.value.goalProtein,
          date = _uiState.value.goalDate
        )
      )) {
        is Response.Fail -> {
          _uiEvent.send(UiEvent.ShowShortToast(result.exception.message))
          _uiEvent.send(UiEvent.HideLoading)
        }
        is Response.Success -> {
          clearInputs()

          _uiEvent.send(
            UiEvent.ShowShortToast("Goal added!")
          )

          _uiEvent.send(
            UiEvent.Navigate(
              navigationType = NavigationType.PopBack(),
              data = mapOf<String, Any>()
            )
          )

          _uiEvent.send(UiEvent.HideLoading)
        }
      }
    }
  }

  private fun onUpdateAddGoalEnabled() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          isGoalButtonEnabled = _uiState.value.goalTitle.isNotEmpty() &&
            _uiState.value.goalProtein.isNotEmpty() &&
            _uiState.value.goalCarbs.isNotEmpty() &&
            _uiState.value.goalFat.isNotEmpty() &&
            _uiState.value.goalCalorie.isNotEmpty() &&
            _uiState.value.goalDate.isNotEmpty()
        )
      }
    }
  }

  private fun clearInputs() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update {
        GoalInsertUiState()
      }
    }
  }
}