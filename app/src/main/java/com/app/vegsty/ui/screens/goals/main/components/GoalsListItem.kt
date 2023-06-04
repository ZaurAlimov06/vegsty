package com.app.vegsty.ui.screens.goals.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.vegsty.data.remote.dto.Goal
import com.app.vegsty.ui.theme.DarkBackgroundColor
import com.app.vegsty.ui.theme.VegstyTheme

@Composable
fun GoalListItem(
  goal: Goal?,
  onGoalClick: (Goal?) -> Unit
) {
  Column(
    modifier = Modifier
      .background(
        color = MaterialTheme.colorScheme.secondary,
        shape = MaterialTheme.shapes.medium
      )
      .clickable(
        onClick = {
          onGoalClick(goal)
        }
      )
      .padding(16.dp)
  ) {

    Text(
      text = goal?.title ?: "",
      textAlign = TextAlign.Start,
      modifier = Modifier
        .fillMaxWidth(),
      style = MaterialTheme.typography.bodyLarge,
      color = DarkBackgroundColor
    )

    Text(
      text = goal?.date ?: "",
      textAlign = TextAlign.Start,
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 4.dp),
      style = MaterialTheme.typography.bodySmall,
      color = MaterialTheme.colorScheme.onBackground
    )
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewGoalListItem() {
  VegstyTheme {
    GoalListItem(
      goal = Goal("Title", "11", "12", "14", "15", "28/04/2023"),
      onGoalClick = {}
    )
  }
}