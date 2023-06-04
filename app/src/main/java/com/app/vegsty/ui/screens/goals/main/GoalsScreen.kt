package com.app.vegsty.ui.screens.goals.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.vegsty.R
import com.app.vegsty.data.remote.dto.Goal
import com.app.vegsty.ui.model.UiEvent
import com.app.vegsty.ui.route.NavigationType
import com.app.vegsty.ui.screens.goals.main.components.GoalListItem
import com.app.vegsty.ui.theme.Dimension
import com.app.vegsty.ui.theme.LocalSpacing
import com.app.vegsty.ui.theme.VegstyTheme
import com.app.vegsty.ui.theme.WhiteColor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun GoalsScreen(
  uiStateFlow: StateFlow<GoalsUiState>,
  uiEventFlow: Flow<UiEvent>,
  onNavigate: (NavigationType, data: Map<String, Any?>?) -> Unit,
  onEvent: (GoalsUiEvent) -> Unit,
  showShortToast: (String?) -> Unit,
  showLongToast: (String?) -> Unit,
  updateLoading: (Boolean) -> Unit,
  spacing: Dimension = LocalSpacing.current
) {
  val uiState by uiStateFlow.collectAsState()

  LaunchedEffect(true) {
    onEvent(GoalsUiEvent.GetAllGoals)

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
      text = stringResource(id = R.string.goals_title_text_goals),
      textAlign = TextAlign.Center,
      modifier = Modifier
        .fillMaxWidth(),
      style = MaterialTheme.typography.bodySmall,
      color = MaterialTheme.colorScheme.onBackground
    )

    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp),
      horizontalArrangement = Arrangement.End
    ) {
      Icon(
        painter = painterResource(id = R.drawable.ic_plus),
        modifier = Modifier
          .clickable(
            interactionSource = remember {
              MutableInteractionSource()
            },
            indication = rememberRipple(
              color = MaterialTheme.colorScheme.secondary
            ),
            onClick = {
              onEvent(GoalsUiEvent.OnAddGoalClick)
            }
          )
          .background(
            color = MaterialTheme.colorScheme.primary,
            shape = MaterialTheme.shapes.medium
          )
          .padding(vertical = 16.dp, horizontal = 36.dp),
        tint = WhiteColor,
        contentDescription = stringResource(id = R.string.common_icon_content_description)
      )
    }

    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .weight(1.0f)
        .padding(top = 16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

      items(uiState.goals.size) { index ->
        GoalListItem(
          goal = uiState.goals[index],
          onGoalClick = {
            onEvent(GoalsUiEvent.OnGoalClick(it))
          }
        )
      }
    }

    Button(
      onClick = {
        onEvent(GoalsUiEvent.OnDisplayProgressClick)
      },
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp, start = 12.dp, end = 12.dp),
      colors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
      ),
      shape = MaterialTheme.shapes.large
    ) {
      Text(
        modifier = Modifier
          .padding(top = 10.dp, bottom = 10.dp),
        text = stringResource(id = R.string.goals_button_display_progress),
        style = MaterialTheme.typography.titleLarge,
        color = WhiteColor
      )
    }
  }
}

@Composable
@Preview(showBackground = true)
fun PreviewGoalsScreen() {
  VegstyTheme {
    GoalsScreen(
      uiStateFlow = MutableStateFlow(
        GoalsUiState(
          listOf(
            Goal("Title", "12", "14", "16", "18", "28/24/2023"),
            Goal("Title", "12", "14", "16", "18", "28/24/2023")
          )
        )
      ),
      uiEventFlow = Channel<UiEvent>().receiveAsFlow(),
      onNavigate = { _, _ -> },
      onEvent = { },
      showShortToast = { },
      showLongToast = { },
      updateLoading = { }
    )
  }
}

