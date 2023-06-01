package com.app.vegsty.ui.screens.favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
fun FavoritesScreen(
  uiEventFlow: Flow<UiEvent>,
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
        text = stringResource(id = R.string.favorites_title_text_favorites),
        textAlign = TextAlign.Center,
        modifier = Modifier
          .fillMaxWidth(),
        style = MaterialTheme.typography.bodySmall,
      )
    }

    items(0) {

    }
  }
}

@Composable
@Preview(showBackground = true)
fun PreviewFavoritesScreen() {
  VegstyTheme {
    FavoritesScreen(
      uiEventFlow = Channel<UiEvent>().receiveAsFlow(),
      onNavigate = { _, _ -> }
    )
  }
}

