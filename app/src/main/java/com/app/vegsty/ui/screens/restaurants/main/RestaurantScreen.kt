package com.app.vegsty.ui.screens.restaurants.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.vegsty.R
import com.app.vegsty.ui.model.UiEvent
import com.app.vegsty.ui.route.NavigationType
import com.app.vegsty.ui.theme.Dimension
import com.app.vegsty.ui.theme.LocalSpacing
import com.app.vegsty.ui.theme.VegstyTheme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun RestaurantScreen(
  uiEventFlow: Flow<UiEvent>,
  onEvent: (RestaurantScreenUiEvent) -> Unit,
  onNavigate: (NavigationType, data: Map<String, Any?>?) -> Unit,
  spacing: Dimension = LocalSpacing.current
) {
  LaunchedEffect(true) {
    uiEventFlow.collect { event ->
      when (event) {
        is UiEvent.Navigate<*> -> {
          onNavigate(event.navigationType, event.data)
        }

        else -> {}
      }
    }
  }
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
        text = stringResource(id = R.string.restaurants_title_text_restaurants),
        textAlign = TextAlign.Center,
        modifier = Modifier
          .fillMaxWidth(),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary
      )
      Button(
        onClick = {
          onEvent(RestaurantScreenUiEvent.OnFirstRestaurantClick)
        },
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 16.dp)
          .size(300.dp, 200.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(20.dp)
      ) {
        Column() {
          Icon(
            painter = painterResource(id = R.drawable.ic_logo_green),
            contentDescription = stringResource(id = R.string.common_icon_content_description),
            tint = Color.Unspecified,
            modifier = Modifier
              .fillMaxWidth()
              .size(150.dp)
          )
          Text(
            text = stringResource(id = R.string.first_restaurant_title),
            modifier = Modifier.padding(start = 10.dp),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge
          )
        }
      }

      Button(
        onClick = {
          onEvent(RestaurantScreenUiEvent.OnSecondRestaurantClick)
        },
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 16.dp)
          .size(300.dp, 200.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(20.dp)
      ) {
        Column() {
          Icon(
            painter = painterResource(id = R.drawable.ic_logo_green),
            contentDescription = stringResource(id = R.string.common_icon_content_description),
            tint = Color.Unspecified,
            modifier = Modifier
              .fillMaxWidth()
              .size(150.dp)
          )
          Text(
            text = stringResource(id = R.string.second_restaurant_title),
            modifier = Modifier.padding(start = 10.dp),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge
          )
        }
      }
    }
  }
}

@Composable
@Preview(showBackground = true)
fun PreviewRestaurantScreen() {
  VegstyTheme {
    RestaurantScreen(
      uiEventFlow = Channel<UiEvent>().receiveAsFlow(),
      onNavigate = { _, _ -> },
      onEvent = {}

    )
  }
}

