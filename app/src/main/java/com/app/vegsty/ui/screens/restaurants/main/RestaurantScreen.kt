package com.app.vegsty.ui.screens.restaurants.main

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
import com.app.vegsty.ui.model.UiEvent
import com.app.vegsty.ui.route.NavigationType
import com.app.vegsty.ui.screens.restaurants.main.components.RestaurantDetailItem
import com.app.vegsty.ui.theme.Dimension
import com.app.vegsty.ui.theme.LocalSpacing
import com.app.vegsty.ui.theme.VegstyTheme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun RestaurantScreen(
  uiStateFlow: StateFlow<RestaurantUiState>,
  uiEventFlow: Flow<UiEvent>,
  onEvent: (RestaurantScreenUiEvent) -> Unit,
  onNavigate: (NavigationType, data: Map<String, Any?>?) -> Unit,
  showShortToast: (String?) -> Unit,
  showLongToast: (String?) -> Unit,
  updateLoading: (Boolean) -> Unit,
  spacing: Dimension = LocalSpacing.current
) {
  val uiState by uiStateFlow.collectAsState()

  LaunchedEffect(true) {
    onEvent(RestaurantScreenUiEvent.GetAllRestaurants)

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
    Text(
      text = stringResource(id = R.string.restaurants_title_text_restaurants),
      textAlign = TextAlign.Center,
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp),
      style = MaterialTheme.typography.bodySmall,
      color = MaterialTheme.colorScheme.onBackground
    )

    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(top = 16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      items(uiState.restaurantList.size) { index ->
        RestaurantDetailItem(
          restaurant = uiState.restaurantList[index],
          onRestaurantClick = {
            onEvent(RestaurantScreenUiEvent.OnRestaurantClick(it))
          }
        )
      }
    }
  }
}

@Composable
@Preview(showBackground = true)
fun PreviewRestaurantScreen() {
  VegstyTheme {
    RestaurantScreen(
      uiStateFlow = MutableStateFlow(
        RestaurantUiState()
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

