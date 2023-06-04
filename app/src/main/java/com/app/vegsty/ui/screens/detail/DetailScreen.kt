package com.app.vegsty.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.vegsty.R
import com.app.vegsty.data.remote.dto.Recipe
import com.app.vegsty.ui.components.RecipeImage
import com.app.vegsty.ui.model.UiEvent
import com.app.vegsty.ui.theme.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun DetailScreen(
  uiEventFlow: Flow<UiEvent>,
  recipe: Recipe?,
  isFromHome: Boolean,
  showShortToast: (String?) -> Unit,
  showLongToast: (String?) -> Unit,
  updateLoading: (Boolean) -> Unit,
  onEvent: (DetailUiEvent) -> Unit,
  spacing: Dimension = LocalSpacing.current
) {
  LaunchedEffect(true) {
    uiEventFlow.collect { event ->
      when (event) {
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
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .weight(1.0f)
    ) {

      item {
        if (recipe != null) {
          RecipeImage(
            imageUrl = recipe.imgUrl,
            onLoadImageAgainClick = {

            },
            shape = RoundedCornerShape(
              16.dp
            )
          )
        }

        if (recipe != null) {
          Text(
            text = recipe.title,
            textAlign = TextAlign.Start,
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 16.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = DarkBackgroundColor
          )
        }

        Text(
          text = "Details",
          textAlign = TextAlign.Start,
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
          style = MaterialTheme.typography.bodyLarge,
          color = DarkBackgroundColor
        )

        if (recipe != null) {
          Text(
            text = recipe.detail,
            textAlign = TextAlign.Start,
            modifier = Modifier
              .fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium,
            color = DarkBackgroundColor
          )
        }

        Text(
          text = "Calorie(Kkal)",
          textAlign = TextAlign.Start,
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
          style = MaterialTheme.typography.bodyLarge,
          color = DarkBackgroundColor
        )

        if (recipe != null) {
          Text(
            text = recipe.calorie,
            textAlign = TextAlign.Start,
            modifier = Modifier
              .fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium,
            color = DarkBackgroundColor
          )
        }

        Text(
          text = "Protein(gram)",
          textAlign = TextAlign.Start,
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
          style = MaterialTheme.typography.bodyLarge,
          color = DarkBackgroundColor
        )

        if (recipe != null) {
          Text(
            text = recipe.protein,
            textAlign = TextAlign.Start,
            modifier = Modifier
              .fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium,
            color = DarkBackgroundColor
          )
        }

        Text(
          text = "Fat(gram)",
          textAlign = TextAlign.Start,
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
          style = MaterialTheme.typography.bodyLarge,
          color = DarkBackgroundColor
        )

        if (recipe != null) {
          Text(
            text = recipe.fat,
            textAlign = TextAlign.Start,
            modifier = Modifier
              .fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium,
            color = DarkBackgroundColor
          )
        }

        Text(
          text = "Carbs(gram)",
          textAlign = TextAlign.Start,
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
          style = MaterialTheme.typography.bodyLarge,
          color = DarkBackgroundColor
        )

        if (recipe != null) {
          Text(
            text = recipe.carbs,
            textAlign = TextAlign.Start,
            modifier = Modifier
              .fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium,
            color = DarkBackgroundColor
          )
        }
      }
    }

    if (isFromHome) {
      Button(
        onClick = {
          onEvent(DetailUiEvent.OnAddToFavorites(recipe))
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
          text = stringResource(id = R.string.detail_button_add_to_favorites),
          style = MaterialTheme.typography.titleLarge,
          color = WhiteColor
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
      isFromHome = true,
      uiEventFlow = Channel<UiEvent>().receiveAsFlow(),
      onEvent = {},
      showShortToast = { },
      showLongToast = { },
      updateLoading = { }
    )
  }
}