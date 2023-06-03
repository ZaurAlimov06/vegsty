package com.app.vegsty.ui.screens.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.vegsty.data.remote.dto.Recipe
import com.app.vegsty.ui.components.RecipeImage
import com.app.vegsty.ui.route.NavigationType
import com.app.vegsty.ui.theme.DarkBackgroundColor
import com.app.vegsty.ui.theme.Dimension
import com.app.vegsty.ui.theme.LocalSpacing
import com.app.vegsty.ui.theme.VegstyTheme

@Composable
fun DetailScreen(
  recipe: Recipe?,
  onNavigate: (NavigationType, data: Map<String, Any?>?) -> Unit,
  spacing: Dimension = LocalSpacing.current
) {
  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .padding(
        horizontal = spacing.spaceScreenHorizontalPadding,
        vertical = spacing.spaceScreenVerticalPadding
      )
  ) {

    item {
      if (recipe != null) {
        RecipeImage(
          imageUrl = recipe.imgUrl,
          onLoadImageAgainClick = {

          }
        )
      }

      if (recipe != null) {
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
      Text(
        text = "Details",
        textAlign = TextAlign.Start,
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 10.dp, vertical = 16.dp),
        style = MaterialTheme.typography.bodyLarge,
        color = DarkBackgroundColor
      )
      if (recipe != null) {
        Text(
          text = recipe.detail,
          textAlign = TextAlign.Start,
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 16.dp),
          style = MaterialTheme.typography.bodyLarge,
          color = DarkBackgroundColor
        )
      }
      Text(
        text = "Calorie(Kkal)",
        textAlign = TextAlign.Start,
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 5.dp, vertical = 8.dp),
        style = MaterialTheme.typography.bodyLarge,
        color = DarkBackgroundColor
      )
      if (recipe != null) {
        Text(
          text = recipe.calorie,
          textAlign = TextAlign.Start,
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 8.dp),
          style = MaterialTheme.typography.bodyLarge,
          color = DarkBackgroundColor
        )
      }
      Text(
        text = "Protein(gram)",
        textAlign = TextAlign.Start,
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 5.dp, vertical = 8.dp),
        style = MaterialTheme.typography.bodyLarge,
        color = DarkBackgroundColor
      )
      if (recipe != null) {
        Text(
          text = recipe.protein,
          textAlign = TextAlign.Start,
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 5.dp),
          style = MaterialTheme.typography.bodyLarge,
          color = DarkBackgroundColor
        )
      }
      Text(
        text = "Fat(gram)",
        textAlign = TextAlign.Start,
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 5.dp, vertical = 8.dp),
        style = MaterialTheme.typography.bodyLarge,
        color = DarkBackgroundColor
      )
      if (recipe != null) {
        Text(
          text = recipe.fat,
          textAlign = TextAlign.Start,
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 8.dp),
          style = MaterialTheme.typography.bodyLarge,
          color = DarkBackgroundColor
        )
      }
      Text(
        text = "Carbs(gram)",
        textAlign = TextAlign.Start,
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 5.dp, vertical = 8.dp),
        style = MaterialTheme.typography.bodyLarge,
        color = DarkBackgroundColor
      )
      if (recipe != null) {
        Text(
          text = recipe.carbs,
          textAlign = TextAlign.Start,
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 8.dp),
          style = MaterialTheme.typography.bodyLarge,
          color = DarkBackgroundColor
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
  VegstyTheme {
    DetailScreen(
      recipe = Recipe(
        id = "",
        imgUrl = "",
        detail = "",
        title = "",
        protein = "12",
        calorie = "14",
        fat = "16",
        carbs = "17"
      ),
      onNavigate = { _, _ -> }
    )
  }
}