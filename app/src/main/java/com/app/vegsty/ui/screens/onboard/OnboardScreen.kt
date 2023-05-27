package com.app.vegsty.ui.screens.onboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
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
import com.app.vegsty.ui.theme.WhiteColor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun OnboardScreen(
  uiEventFlow: Flow<UiEvent>,
  onNavigate: (NavigationType, data: Map<String, Any?>?) -> Unit,
  onEvent: (OnboardScreenUiEvent) -> Unit,
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
      ),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    item {

      Icon(
        modifier = Modifier
          .padding(top = 30.dp),
        tint = Color.Unspecified,
        painter = painterResource(id = R.drawable.ic_logo_green),
        contentDescription = stringResource(id = R.string.common_icon_content_description)
      )

      Icon(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 20.dp),
        tint = Color.Unspecified,
        painter = painterResource(id = R.drawable.ic_onboard_banner),
        contentDescription = stringResource(id = R.string.common_icon_content_description)
      )

      Text(
        text = stringResource(id = R.string.onboard_banner_title_text_find_recipes),
        textAlign = TextAlign.Center,
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 20.dp),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onBackground
      )

      Text(
        text = stringResource(id = R.string.onboard_banner_body_text_perfect_app),
        textAlign = TextAlign.Center,
        modifier = Modifier
          .fillMaxWidth()
          .padding(start = 20.dp, end = 20.dp, top = 20.dp),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onBackground
      )

      Button(
        onClick = {
          onEvent(OnboardScreenUiEvent.OnGetStartedClick)
        },
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 20.dp, start = 12.dp, end = 12.dp),
        colors = ButtonDefaults.buttonColors(
          containerColor = MaterialTheme.colorScheme.primary,
          contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = MaterialTheme.shapes.large
      ) {
        Text(
          modifier = Modifier
            .padding(top = 12.dp, bottom = 12.dp),
          text = stringResource(id = R.string.onboard_button_get_started),
          style = MaterialTheme.typography.titleLarge,
          color = WhiteColor
        )
      }

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 20.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.Center
      ) {
        Text(
          text = stringResource(id = R.string.onboard_text_already_have_account),
          textAlign = TextAlign.Center,
          modifier = Modifier
            .clickable { },
          style = MaterialTheme.typography.bodyLarge,
          color = MaterialTheme.colorScheme.onBackground
        )

        Text(
          text = stringResource(id = R.string.onboard_text_log_in),
          textAlign = TextAlign.Center,
          color = MaterialTheme.colorScheme.primary,
          modifier = Modifier
            .padding(start = 5.dp)
            .clickable(
              onClick = {
                onEvent(OnboardScreenUiEvent.OnLogInClick)
              }
            ),
          style = MaterialTheme.typography.bodyLarge
        )
      }
    }
  }
}

@Composable
@Preview(showBackground = true)
fun PreviewOnboardScreen() {
  VegstyTheme {
    OnboardScreen(
      uiEventFlow = Channel<UiEvent>().receiveAsFlow(),
      onNavigate = { _, _ -> },
      onEvent = {}
    )
  }
}
