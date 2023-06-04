package com.app.vegsty.ui.screens.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.vegsty.R
import com.app.vegsty.data.remote.dto.Recipe
import com.app.vegsty.ui.components.RecipeListItem
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

@Composable
fun FavoritesScreen(
  uiStateFlow: StateFlow<FavoritesUiState>,
  uiEventFlow: Flow<UiEvent>,
  onNavigate: (NavigationType, data: Map<String, Any?>?) -> Unit,
  onEvent: (FavoritesUiEvent) -> Unit,
  spacing: Dimension = LocalSpacing.current
) {
  val uiState by uiStateFlow.collectAsState()

  LaunchedEffect(true) {
    onEvent(FavoritesUiEvent.GetAllFavorites)

    uiEventFlow.collect { event ->
      when (event) {
        is UiEvent.Navigate<*> -> {
          onNavigate(event.navigationType, event.data)
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
      text = stringResource(id = R.string.favorites_title_text_favorites),
      textAlign = TextAlign.Center,
      modifier = Modifier
        .fillMaxWidth(),
      style = MaterialTheme.typography.bodySmall,
      color = MaterialTheme.colorScheme.onBackground
    )

    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(
          top = 20.dp
        ),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

      items(uiState.favoriteRecipes.size) { index ->
        uiState.favoriteRecipes[index]?.let { recipe ->
          RecipeListItem(
            recipe = recipe,
            onRecipeClick = {
              onEvent(FavoritesUiEvent.OnFavoriteRecipeClick(it))
            }
          )
        }
      }
    }
  }
}

@Composable
@Preview(showBackground = true)
fun PreviewFavoritesScreen() {
  VegstyTheme {
    FavoritesScreen(
      uiStateFlow = MutableStateFlow(
        FavoritesUiState(
          favoriteRecipes = listOf(
            Recipe("", "", "detail1", "title1", "14", "15", "16", "17"),
            Recipe("", "", "detail2", "title2", "14", "15", "16", "17")
          )
        )
      ),
      uiEventFlow = Channel<UiEvent>().receiveAsFlow(),
      onNavigate = { _, _ -> },
      onEvent = { }
    )
  }
}

