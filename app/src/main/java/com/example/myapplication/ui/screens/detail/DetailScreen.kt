package com.example.myapplication.ui.screens.detail

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
import com.example.myapplication.data.remote.dto.Recipe
import com.example.myapplication.ui.route.NavigationType
import com.example.myapplication.ui.theme.Dimension
import com.example.myapplication.ui.theme.LocalSpacing
import com.example.myapplication.ui.theme.VegstyTheme

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
        id = 1,
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