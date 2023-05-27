package com.app.vegsty.ui.components

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
import com.app.vegsty.data.remote.dto.Recipe
import com.app.vegsty.ui.theme.DarkBackgroundColor
import com.app.vegsty.ui.theme.VegstyTheme

@Composable
fun RecipeListItem(
  recipe: Recipe,
  onRecipeClick: (Recipe) -> Unit
) {
  Column(
    modifier = Modifier
      .background(
        color = MaterialTheme.colorScheme.secondary,
        shape = MaterialTheme.shapes.medium
      )
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
        .fillMaxWidth()
        .padding(horizontal = 10.dp, vertical = 16.dp),
      style = MaterialTheme.typography.bodyLarge,
      color = DarkBackgroundColor
    )
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewRecipeListItem() {
  VegstyTheme {
    RecipeListItem(
      recipe = Recipe(1, "", "detail1", "title1", 14, 15, 16, 17),
      onRecipeClick = {}
    )
  }
}