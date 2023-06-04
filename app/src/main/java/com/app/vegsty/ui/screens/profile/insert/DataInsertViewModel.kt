package com.app.vegsty.ui.screens.profile.insert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vegsty.data.remote.dto.Recipe
import com.app.vegsty.data.remote.dto.Restaurant
import com.app.vegsty.ui.model.ExceptionHandler
import com.app.vegsty.ui.model.Response
import com.app.vegsty.ui.model.UiEvent
import com.app.vegsty.ui.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataInsertViewModel @Inject constructor(
  private val mainRepository: MainRepository
) : ViewModel() {
  private val _uiState = MutableStateFlow(DataInsertUiState())
  val uiState: StateFlow<DataInsertUiState> = _uiState.asStateFlow()

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onEvent(dataInsertUiEvent: DataInsertUiEvent) {
    when (dataInsertUiEvent) {
      is DataInsertUiEvent.OnInsertRecipe -> {
        onInsertRecipe()
      }
      is DataInsertUiEvent.OnInsertRestaurant -> {
        onInsertRestaurant()
      }
      is DataInsertUiEvent.OnUpdateRecipeCalorie -> {
        onUpdateRecipeCalorie(dataInsertUiEvent.recipeCalorie)
      }
      is DataInsertUiEvent.OnUpdateRecipeCarbs -> {
        onUpdateRecipeCarbs(dataInsertUiEvent.recipeCarbs)
      }
      is DataInsertUiEvent.OnUpdateRecipeDetail -> {
        onUpdateRecipeDetail(dataInsertUiEvent.recipeDetail)
      }
      is DataInsertUiEvent.OnUpdateRecipeFat -> {
        onUpdateRecipeFat(dataInsertUiEvent.recipeFat)
      }
      is DataInsertUiEvent.OnUpdateRecipeImgUrl -> {
        onUpdateRecipeImgUrl(dataInsertUiEvent.recipeImgUrl)
      }
      is DataInsertUiEvent.OnUpdateRecipeProtein -> {
        onUpdateRecipeProtein(dataInsertUiEvent.recipeProtein)
      }
      is DataInsertUiEvent.OnUpdateRecipeTitle -> {
        onUpdateRecipeTitle(dataInsertUiEvent.recipeTitle)
      }
      is DataInsertUiEvent.OnUpdateRestaurantName -> {
        onUpdateRestaurantName(dataInsertUiEvent.restaurantName)
      }
      is DataInsertUiEvent.OnUpdateRestaurantAddress -> {
        onUpdateRestaurantAddress(dataInsertUiEvent.restaurantAddress)
      }
      is DataInsertUiEvent.OnUpdateRestaurantDetail -> {
        onUpdateRestaurantDetail(dataInsertUiEvent.restaurantDetail)
      }
      is DataInsertUiEvent.OnUpdateRestaurantPhoto -> {
        onUpdateRestaurantPhoto(dataInsertUiEvent.restaurantPhoto)
      }
    }
  }

  private fun onInsertRecipe() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(UiEvent.ShowLoading)
      val recipe = Recipe(
        title = _uiState.value.recipeTitle,
        detail = _uiState.value.recipeDetail,
        imgUrl = _uiState.value.recipeImgUrl,
        protein = _uiState.value.recipeProtein,
        calorie = _uiState.value.recipeCalorie,
        fat = _uiState.value.recipeFat,
        carbs = _uiState.value.recipeCarbs,
      )

      when (val result = mainRepository.insertRecipe(recipe)) {
        is Response.Fail -> {
          _uiEvent.send(UiEvent.ShowShortToast(result.exception.message))
          _uiEvent.send(UiEvent.HideLoading)
        }
        is Response.Success -> {
          clearRecipeInputs()
          _uiEvent.send(UiEvent.ShowShortToast("Recipe successfully inserted!"))
          _uiEvent.send(UiEvent.HideLoading)
        }
      }
    }
  }

  private fun onInsertRestaurant() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiEvent.send(UiEvent.ShowLoading)
      val restaurant = Restaurant(
        name = _uiState.value.restaurantName,
        detail = _uiState.value.restaurantDetail,
        address = _uiState.value.restaurantAddress,
        photo = _uiState.value.restaurantPhoto
      )

      when (val result = mainRepository.insertRestaurant(restaurant)) {
        is Response.Fail -> {
          _uiEvent.send(UiEvent.ShowShortToast(result.exception.message))
          _uiEvent.send(UiEvent.HideLoading)
        }
        is Response.Success -> {
          clearRestaurantInputs()
          _uiEvent.send(UiEvent.ShowShortToast("Restaurant successfully inserted!"))
          _uiEvent.send(UiEvent.HideLoading)
        }
      }
    }
  }

  private fun onUpdateRecipeImgUrl(recipeImgUrl: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          recipeImgUrl = recipeImgUrl
        )
      }
      onUpdateRecipeButtonEnabled()
    }
  }

  private fun onUpdateRecipeDetail(recipeDetail: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          recipeDetail = recipeDetail
        )
      }
      onUpdateRecipeButtonEnabled()
    }
  }

  private fun onUpdateRecipeTitle(recipeTitle: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          recipeTitle = recipeTitle
        )
      }
      onUpdateRecipeButtonEnabled()
    }
  }

  private fun onUpdateRecipeProtein(recipeProtein: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          recipeProtein = recipeProtein
        )
      }
      onUpdateRecipeButtonEnabled()
    }
  }

  private fun onUpdateRecipeCalorie(recipeCalorie: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          recipeCalorie = recipeCalorie
        )
      }
      onUpdateRecipeButtonEnabled()
    }
  }

  private fun onUpdateRecipeFat(recipeFat: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          recipeFat = recipeFat
        )
      }
      onUpdateRecipeButtonEnabled()
    }
  }

  private fun onUpdateRecipeCarbs(recipeCarbs: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          recipeCarbs = recipeCarbs
        )
      }
      onUpdateRecipeButtonEnabled()
    }
  }

  private fun onUpdateRestaurantName(restaurantName: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          restaurantName = restaurantName
        )
      }
      onUpdateRestaurantButtonEnabled()
    }
  }

  private fun onUpdateRestaurantDetail(restaurantDetail: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          restaurantDetail = restaurantDetail
        )
      }
      onUpdateRestaurantButtonEnabled()
    }
  }

  private fun onUpdateRestaurantAddress(restaurantAddress: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          restaurantAddress = restaurantAddress
        )
      }
      onUpdateRestaurantButtonEnabled()
    }
  }

  private fun onUpdateRestaurantPhoto(restaurantPhoto: String) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          restaurantPhoto = restaurantPhoto
        )
      }
      onUpdateRestaurantButtonEnabled()
    }
  }

  private fun onUpdateRecipeButtonEnabled() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          isRecipeButtonEnabled = _uiState.value.recipeImgUrl.isNotEmpty() &&
            _uiState.value.recipeDetail.isNotEmpty() &&
            _uiState.value.recipeTitle.isNotEmpty() &&
            _uiState.value.recipeProtein.isNotEmpty() &&
            _uiState.value.recipeCalorie.isNotEmpty() &&
            _uiState.value.recipeFat.isNotEmpty() &&
            _uiState.value.recipeCarbs.isNotEmpty()
        )
      }
    }
  }

  private fun onUpdateRestaurantButtonEnabled() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          isRestaurantButtonEnabled = _uiState.value.restaurantName.isNotEmpty()
        )
      }
    }
  }

  private fun clearRecipeInputs() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          recipeImgUrl = "",
          recipeDetail = "",
          recipeTitle = "",
          recipeProtein = "",
          recipeCalorie = "",
          recipeFat = "",
          recipeCarbs = "",
          isRecipeButtonEnabled = false
        )
      }
    }
  }

  private fun clearRestaurantInputs() {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(
          restaurantName = "",
          isRestaurantButtonEnabled = false
        )
      }
    }
  }
}