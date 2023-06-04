package com.app.vegsty.ui.screens.goals.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.vegsty.R
import com.app.vegsty.data.remote.dto.Goal
import com.app.vegsty.ui.theme.Dimension
import com.app.vegsty.ui.theme.LocalSpacing
import com.app.vegsty.ui.theme.VegstyTheme

@Composable
fun GoalDetailScreen(
  goal: Goal?,
  spacing: Dimension = LocalSpacing.current
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(
        horizontal = spacing.spaceScreenHorizontalPadding,
        vertical = spacing.spaceScreenVerticalPadding
      )
  ) {
    Text(
      text = stringResource(id = R.string.goal_detail_title_text_goal_detail),
      textAlign = TextAlign.Center,
      modifier = Modifier
        .fillMaxWidth(),
      style = MaterialTheme.typography.bodySmall,
      color = MaterialTheme.colorScheme.onBackground
    )

    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(top = 16.dp)
    ) {

      item {
        Row(
          modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = stringResource(id = R.string.goal_detail_label_goal_title),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium,
          )

          Text(
            text = goal?.title ?: "",
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
          )
        }

        Row(
          modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = stringResource(id = R.string.goal_detail_label_goal_protein),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium,
          )

          Text(
            text = goal?.protein ?: "",
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
          )
        }

        Row(
          modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = stringResource(id = R.string.goal_detail_label_goal_calorie),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium,
          )

          Text(
            text = goal?.calorie ?: "",
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
          )
        }

        Row(
          modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = stringResource(id = R.string.goal_detail_label_goal_fat),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium,
          )

          Text(
            text = goal?.fat ?: "",
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
          )
        }

        Row(
          modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = stringResource(id = R.string.goal_detail_label_goal_carbs),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium,
          )

          Text(
            text = goal?.carbs ?: "",
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
          )
        }

        Row(
          modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = stringResource(id = R.string.goal_detail_label_goal_date),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium,
          )

          Text(
            text = goal?.date ?: "",
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
          )
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewGoalDetailScreen() {
  VegstyTheme {
    GoalDetailScreen(
      goal = Goal(
        title = "Title",
        protein = "12",
        calorie = "14",
        fat = "16",
        carbs = "17",
        date = "23/08/2023"
      )
    )
  }
}