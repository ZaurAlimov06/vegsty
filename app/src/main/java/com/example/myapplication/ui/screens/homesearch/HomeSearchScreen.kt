package com.example.myapplication.ui.screens.homesearch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.data.remote.dto.Recipe
import com.example.myapplication.ui.components.RecipeListItem
import com.example.myapplication.ui.model.UiEvent
import com.example.myapplication.ui.route.NavigationType
import com.example.myapplication.ui.theme.Dimension
import com.example.myapplication.ui.theme.LocalSpacing
import com.example.myapplication.ui.theme.VegstyTheme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun HomeSearchScreen(
  uiStateFlow: StateFlow<HomeUiState>,
  uiEventFlow: Flow<UiEvent>,
  onNavigate: (NavigationType, data: Map<String, Any?>?) -> Unit,
  onEvent: (HomeUiEvent) -> Unit,
  spacing: Dimension = LocalSpacing.current
) {
  val uiState by uiStateFlow.collectAsState()

  LaunchedEffect(true) {
    uiEventFlow.collect { event ->
      when (event) {
        is UiEvent.Navigate<*> -> {
          onNavigate(event.navigationType, event.data)
        }
      }
    }
  }

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .padding(
        horizontal = spacing.spaceScreenHorizontalPadding,
        vertical = spacing.spaceScreenVerticalPadding
      ),
    verticalArrangement = Arrangement.spacedBy(spacing.spaceListItemPadding)
  ) {

    items(uiState.recipeList.size) { index ->
      RecipeListItem(
        recipe = uiState.recipeList[index],
        onRecipeClick = {
          onEvent(HomeUiEvent.OnRecipeClick(it))
        }
      )
    }
  }
}

@Composable
@Preview(showBackground = true)
fun PreviewHomeSearchScreen() {
  VegstyTheme {
    HomeSearchScreen(
      uiStateFlow = MutableStateFlow(
        HomeUiState(
          recipeList = listOf(
            Recipe(1, "", "detail1", "title1", 14, 15, 16, 17),
            Recipe(2, "", "detail2", "title2", 14, 15, 16, 17)
          )
        )
      ),
      uiEventFlow = Channel<UiEvent>().receiveAsFlow(),
      onNavigate = { _, _ -> },
      onEvent = {}
    )
  }
}

