package com.example.myapplication.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.myapplication.data.remote.dto.Recipe

@Composable
fun RecipeListItem(
  recipe: Recipe,
  onRecipeClick: (Recipe) -> Unit
) {
  Column(
    modifier = Modifier
      .clickable(
        onClick = {
          onRecipeClick(recipe)
        }
      )
  ) {
    Text(
      text = recipe.title,
      textAlign = TextAlign.Start,
      modifier = Modifier
        .fillMaxWidth(),
      style = MaterialTheme.typography.bodySmall,
    )

  }
}