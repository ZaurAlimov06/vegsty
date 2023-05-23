package com.example.myapplication.ui.screens.homesearch

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.model.UiEvent
import com.example.myapplication.ui.route.NavigationType
import com.example.myapplication.ui.theme.Dimension
import com.example.myapplication.ui.theme.LocalSpacing
import com.example.myapplication.ui.theme.VegstyTheme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun HomeSearchScreen(
  uiEventFlow: Flow<UiEvent>,
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


    items(0) {

    }
  }
}

@Composable
@Preview(showBackground = true)
fun PreviewHomeSearchScreen() {
  VegstyTheme {
    HomeSearchScreen(
      uiEventFlow = Channel<UiEvent>().receiveAsFlow(),
      onNavigate = { _, _ -> }
    )
  }
}

