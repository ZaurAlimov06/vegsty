package com.app.vegsty.ui.screens.goals.insert

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.app.vegsty.ui.theme.VegstyTheme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

@ExperimentalMaterial3Api
@Composable
fun GoalInsertScreen(
  uiStateFlow: StateFlow<GoalInsertUiState>,
  uiEventFlow: Flow<UiEvent>,
  onNavigate: (NavigationType, data: Map<String, Any?>?) -> Unit,
  showShortToast: (String?) -> Unit,
  showLongToast: (String?) -> Unit,
  updateLoading: (Boolean) -> Unit,
  onEvent: (GoalInsertUiEvent) -> Unit,
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
      text = stringResource(id = R.string.add_goal_title_text_add_goal),
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
        OutlinedTextField(
          value = uiState.goalTitle,
          label = {
            Text(
              text = stringResource(id = R.string.add_goal_label_goal_title),
              style = MaterialTheme.typography.titleMedium
            )
          },
          onValueChange = {
            onEvent(GoalInsertUiEvent.OnUpdateGoalTitle(it))
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
          value = uiState.goalProtein,
          label = {
            Text(
              text = stringResource(id = R.string.add_goal_label_goal_protein),
              style = MaterialTheme.typography.titleMedium
            )
          },
          onValueChange = {
            onEvent(GoalInsertUiEvent.OnUpdateGoalProtein(it))
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
          value = uiState.goalCalorie,
          label = {
            Text(
              text = stringResource(id = R.string.add_goal_label_goal_calorie),
              style = MaterialTheme.typography.titleMedium
            )
          },
          onValueChange = {
            onEvent(GoalInsertUiEvent.OnUpdateGoalCalorie(it))
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
          value = uiState.goalFat,
          label = {
            Text(
              text = stringResource(id = R.string.add_goal_label_goal_fat),
              style = MaterialTheme.typography.titleMedium
            )
          },
          onValueChange = {
            onEvent(GoalInsertUiEvent.OnUpdateGoalFat(it))
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
          value = uiState.goalCarbs,
          label = {
            Text(
              text = stringResource(id = R.string.add_goal_label_goal_carbs),
              style = MaterialTheme.typography.titleMedium
            )
          },
          onValueChange = {
            onEvent(GoalInsertUiEvent.OnUpdateGoalCarbs(it))
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
          value = uiState.goalDate,
          label = {
            Text(
              text = stringResource(id = R.string.add_goal_label_goal_date),
              style = MaterialTheme.typography.titleMedium
            )
          },
          onValueChange = {
            onEvent(GoalInsertUiEvent.OnUpdateGoalDate(it))
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
            onEvent(GoalInsertUiEvent.OnInsertGoal)
          },
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
          enabled = uiState.isGoalButtonEnabled,
          colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
          ),
          shape = MaterialTheme.shapes.extraLarge
        ) {
          Text(
            text = stringResource(id = R.string.add_goal_button_add_goal),
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
fun PreviewGoalInsertScreen() {
  VegstyTheme {
    GoalInsertScreen(
      uiStateFlow = MutableStateFlow(
        GoalInsertUiState()
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


