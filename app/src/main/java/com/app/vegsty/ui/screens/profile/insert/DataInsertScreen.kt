package com.app.vegsty.ui.screens.profile.insert

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.vegsty.R
import com.app.vegsty.ui.model.UiEvent
import com.app.vegsty.ui.route.NavigationType
import com.app.vegsty.ui.theme.Dimension
import com.app.vegsty.ui.theme.LocalSpacing
import com.app.vegsty.ui.theme.SeparatorColor
import com.app.vegsty.ui.theme.VegstyTheme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

@ExperimentalMaterial3Api
@Composable
fun DataInsertScreen(
  uiStateFlow: StateFlow<DataInsertUiState>,
  uiEventFlow: Flow<UiEvent>,
  onNavigate: (NavigationType, data: Map<String, Any?>?) -> Unit,
  showShortToast: (String?) -> Unit,
  showLongToast: (String?) -> Unit,
  updateLoading: (Boolean) -> Unit,
  onEvent: (DataInsertUiEvent) -> Unit,
  spacing: Dimension = LocalSpacing.current
) {
  val uiState by uiStateFlow.collectAsState()

  LaunchedEffect(true) {
    uiEventFlow.collect { event ->
      when (event) {
        is UiEvent.Navigate<*> -> {
          onNavigate(event.navigationType, event.data)
        }
        is UiEvent.ShowShortToast -> {
          showShortToast(event.message)
        }
        is UiEvent.ShowLongToast -> {
          showLongToast(event.message)
        }
        is UiEvent.ShowLoading -> {
          updateLoading(true)
        }
        is UiEvent.HideLoading -> {
          updateLoading(false)
        }
        else -> {}
      }
    }
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(
        horizontal = spacing.spaceScreenHorizontalPadding,
        vertical = spacing.spaceScreenVerticalPadding
      )
  ) {
    Text(
      text = stringResource(id = R.string.data_insert_text_data_insert),
      textAlign = TextAlign.Center,
      modifier = Modifier
        .fillMaxWidth(),
      style = MaterialTheme.typography.bodySmall,
      color = MaterialTheme.colorScheme.onBackground
    )

    LazyColumn(
      modifier = Modifier
        .padding(top = 10.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      item {
        Text(
          text = stringResource(id = R.string.data_insert_text_recipe),
          textAlign = TextAlign.Start,
          modifier = Modifier
            .fillMaxWidth(),
          style = MaterialTheme.typography.titleLarge,
        )

        OutlinedTextField(
          value = uiState.recipeTitle,
          label = {
            Text(
              text = stringResource(id = R.string.data_insert_label_recipe_title),
              style = MaterialTheme.typography.titleMedium
            )
          },
          onValueChange = {
            onEvent(DataInsertUiEvent.OnUpdateRecipeTitle(it))
          },
          modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth(),
          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
          ),
          singleLine = true,
          maxLines = 1,
          colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground
          ),
          textStyle = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
          value = uiState.recipeDetail,
          label = {
            Text(
              text = stringResource(id = R.string.data_insert_label_recipe_detail),
              style = MaterialTheme.typography.titleMedium
            )
          },
          onValueChange = {
            onEvent(DataInsertUiEvent.OnUpdateRecipeDetail(it))
          },
          modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth(),
          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
          ),
          singleLine = true,
          maxLines = 1,
          colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground
          ),
          textStyle = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
          value = uiState.recipeImgUrl,
          label = {
            Text(
              text = stringResource(id = R.string.data_insert_label_img_url),
              style = MaterialTheme.typography.titleMedium
            )
          },
          onValueChange = {
            onEvent(DataInsertUiEvent.OnUpdateRecipeImgUrl(it))
          },
          modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth(),
          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
          ),
          singleLine = true,
          maxLines = 1,
          colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground
          ),
          textStyle = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
          value = uiState.recipeProtein,
          label = {
            Text(
              text = stringResource(id = R.string.data_insert_label_recipe_protein),
              style = MaterialTheme.typography.titleMedium
            )
          },
          onValueChange = {
            onEvent(DataInsertUiEvent.OnUpdateRecipeProtein(it))
          },
          modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth(),
          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Next
          ),
          singleLine = true,
          maxLines = 1,
          colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground
          ),
          textStyle = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
          value = uiState.recipeCalorie,
          label = {
            Text(
              text = stringResource(id = R.string.data_insert_label_recipe_calorie),
              style = MaterialTheme.typography.titleMedium
            )
          },
          onValueChange = {
            onEvent(DataInsertUiEvent.OnUpdateRecipeCalorie(it))
          },
          modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth(),
          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Next
          ),
          singleLine = true,
          maxLines = 1,
          colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground
          ),
          textStyle = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
          value = uiState.recipeFat,
          label = {
            Text(
              text = stringResource(id = R.string.data_insert_label_recipe_fat),
              style = MaterialTheme.typography.titleMedium
            )
          },
          onValueChange = {
            onEvent(DataInsertUiEvent.OnUpdateRecipeFat(it))
          },
          modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth(),
          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Next
          ),
          singleLine = true,
          maxLines = 1,
          colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground
          ),
          textStyle = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
          value = uiState.recipeCarbs,
          label = {
            Text(
              text = stringResource(id = R.string.data_insert_label_recipe_carbs),
              style = MaterialTheme.typography.titleMedium
            )
          },
          onValueChange = {
            onEvent(DataInsertUiEvent.OnUpdateRecipeCarbs(it))
          },
          modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth(),
          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Done
          ),
          singleLine = true,
          maxLines = 1,
          colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground
          ),
          textStyle = MaterialTheme.typography.titleMedium
        )

        Button(
          onClick = {
            onEvent(DataInsertUiEvent.OnInsertRecipe)
          },
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
          enabled = uiState.isRecipeButtonEnabled,
          colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
          ),
          shape = MaterialTheme.shapes.extraLarge
        ) {
          Text(
            text = stringResource(id = R.string.data_insert_button_insert_recipe),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge
          )
        }

        Spacer(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 30.dp)
            .height(1.dp)
            .background(SeparatorColor)
        )
      }

      item {
        Text(
          text = stringResource(id = R.string.data_insert_text_restaurant),
          textAlign = TextAlign.Start,
          modifier = Modifier
            .fillMaxWidth(),
          style = MaterialTheme.typography.titleLarge,
        )

        OutlinedTextField(
          value = uiState.restaurantName,
          label = {
            Text(
              text = stringResource(id = R.string.data_insert_label_restaurant_name),
              style = MaterialTheme.typography.titleMedium
            )
          },
          onValueChange = {
            onEvent(DataInsertUiEvent.OnUpdateRestaurantName(it))
          },
          modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth(),
          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
          ),
          singleLine = true,
          maxLines = 1,
          colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground
          ),
          textStyle = MaterialTheme.typography.titleMedium
        )

        Button(
          onClick = {
            onEvent(DataInsertUiEvent.OnInsertRestaurant)
          },
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
          enabled = uiState.isRestaurantButtonEnabled,
          colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
          ),
          shape = MaterialTheme.shapes.extraLarge
        ) {
          Text(
            text = stringResource(id = R.string.data_insert_button_insert_restaurant),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge
          )
        }
      }
    }
  }
}

@ExperimentalMaterial3Api
@Composable
@Preview(showBackground = true)
fun PreviewDataInsertScreen() {
  VegstyTheme {
    DataInsertScreen(
      uiStateFlow = MutableStateFlow(
        DataInsertUiState()
      ),
      uiEventFlow = Channel<UiEvent>().receiveAsFlow(),
      showShortToast = { },
      showLongToast = { },
      updateLoading = { },
      onNavigate = { _, _ -> },
      onEvent = {}
    )
  }
}


