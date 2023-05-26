package com.example.myapplication.ui.screens.profile.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.model.UiEvent
import com.example.myapplication.ui.route.NavigationType
import com.example.myapplication.ui.theme.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun SettingsScreen(
  uiStateFlow: StateFlow<SettingsUiState>,
  uiEventFlow: Flow<UiEvent>,
  onNavigate: (NavigationType, data: Map<String, Any?>?) -> Unit,
  onChangeTheme: (Boolean) -> Unit,
  onEvent: (SettingsUiEvent) -> Unit,
  spacing: Dimension = LocalSpacing.current
) {
  val uiState by uiStateFlow.collectAsState()

  LaunchedEffect(true) {
    uiEventFlow.collect { event ->
      when (event) {
        is UiEvent.Navigate<*> -> {
          onNavigate(event.navigationType, event.data)
        }
        is UiEvent.ChangeTheme -> {
          onChangeTheme(event.isDark)
        }
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
      text = stringResource(id = R.string.settings_title_text_profile),
      textAlign = TextAlign.Center,
      modifier = Modifier
        .fillMaxWidth(),
      style = MaterialTheme.typography.bodyMedium,
      color = MaterialTheme.colorScheme.onBackground
    )

    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = stringResource(id = R.string.settings_text_toggle_theme),
        textAlign = TextAlign.Start,
        modifier = Modifier
          .weight(1.0f),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onBackground
      )

      Switch(
        checked = uiState.themeState,
        onCheckedChange = {
          onEvent(SettingsUiEvent.OnChangeTheme(it))
        },
        colors = SwitchDefaults.colors(
          checkedThumbColor = BlackColor,
          checkedTrackColor = WhiteColor,
          checkedBorderColor = BlackColor,
          checkedIconColor = WhiteColor,
          uncheckedThumbColor = WhiteColor,
          uncheckedTrackColor = BlackColor,
          uncheckedBorderColor = WhiteColor,
          uncheckedIconColor = BlackColor
        )
      )
    }
  }
}

@Composable
@Preview(showBackground = true)
fun PreviewOnboardScreen() {
  VegstyTheme {
    SettingsScreen(
      uiStateFlow = MutableStateFlow(
        SettingsUiState()
      ),
      uiEventFlow = Channel<UiEvent>().receiveAsFlow(),
      onNavigate = { _, _ -> },
      onChangeTheme = { },
      onEvent = {}
    )
  }
}
