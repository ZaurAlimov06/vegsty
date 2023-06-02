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
import com.app.vegsty.data.remote.dto.Recipe
import com.app.vegsty.ui.route.NavigationType
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
      Text(
        text = recipe?.title ?: "",
        textAlign = TextAlign.Start,
        modifier = Modifier
          .fillMaxWidth(),
        style = MaterialTheme.typography.bodySmall,
      )
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
        protein = 12,
        calorie = 14,
        fat = 16,
        carbs = 17
      ),
      onNavigate = { _, _ -> }
    )
  }
}