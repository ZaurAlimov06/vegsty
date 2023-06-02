package com.app.vegsty.ui.screens.homesearch

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.vegsty.R
import com.app.vegsty.data.remote.dto.Recipe
import com.app.vegsty.ui.components.RecipeListItem
import com.app.vegsty.ui.model.UiEvent
import com.app.vegsty.ui.route.NavigationType
import com.app.vegsty.ui.theme.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

@ExperimentalMaterial3Api
@Composable
fun HomeSearchScreen(
  uiStateFlow: StateFlow<HomeUiState>,
  uiEventFlow: Flow<UiEvent>,
  onNavigate: (NavigationType, data: Map<String, Any?>?) -> Unit,
  showShortToast: (String?) -> Unit,
  showLongToast: (String?) -> Unit,
  updateLoading: (Boolean) -> Unit,
  onEvent: (HomeUiEvent) -> Unit,
  spacing: Dimension = LocalSpacing.current
) {
  val uiState by uiStateFlow.collectAsState()

  var searchState by remember {
    mutableStateOf("")
  }

  LaunchedEffect(true) {
    uiEventFlow.collect { event ->
      when (event) {
        is UiEvent.Navigate<*> -> {
          onNavigate(event.navigationType, event.data)
        }
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

    OutlinedTextField(
      value = searchState,
      placeholder = { Text(stringResource(id = R.string.homesearch_label_search)) },
      onValueChange = {
        searchState = it
      },
      shape = MaterialTheme.shapes.large,
      modifier = Modifier
        .fillMaxWidth(),
      leadingIcon = {
        Icon(
          painter = painterResource(id = R.drawable.ic_search),
          contentDescription = stringResource(id = R.string.common_icon_content_description),
          tint = UnselectColor
        )
      },
      colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = GrayBackgroundColor,
        unfocusedBorderColor = GrayBackgroundColor,
        containerColor = GrayBackgroundColor,
        placeholderColor = UnselectColor,
        textColor = UnselectColor
      ),
      singleLine = true,
      maxLines = 1
    )

    LazyColumn(
      modifier = Modifier
        .padding(top = 20.dp),
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
}

@ExperimentalMaterial3Api
@Composable
@Preview(showBackground = true)
fun PreviewHomeSearchScreen() {
  VegstyTheme {
    HomeSearchScreen(
      uiStateFlow = MutableStateFlow(
        HomeUiState(
          recipeList = listOf(
            Recipe("", "", "detail1", "title1", 14, 15, 16, 17),
            Recipe("", "", "detail2", "title2", 14, 15, 16, 17)
          )
        )
      ),
      uiEventFlow = Channel<UiEvent>().receiveAsFlow(),
      onNavigate = { _, _ -> },
      onEvent = {},
      showShortToast = { },
      showLongToast = { },
      updateLoading = { }
    )
  }
}

