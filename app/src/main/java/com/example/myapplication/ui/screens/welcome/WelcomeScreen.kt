package com.example.myapplication.ui.screens.welcome

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.model.UiEvent
import com.example.myapplication.ui.route.NavigationType
import com.example.myapplication.ui.screens.welcome.components.Login
import com.example.myapplication.ui.screens.welcome.components.Register
import com.example.myapplication.ui.screens.welcome.components.WelcomeSwitch
import com.example.myapplication.ui.theme.Dimension
import com.example.myapplication.ui.theme.HalfBlackColor
import com.example.myapplication.ui.theme.LocalSpacing
import com.example.myapplication.ui.theme.VegstyTheme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

@ExperimentalMaterial3Api
@Composable
fun WelcomeScreen(
  uiEventFlow: Flow<UiEvent>,
  onNavigate: (NavigationType, data: Map<String, Any?>?) -> Unit,
  onEvent: (WelcomeScreenUiEvent) -> Unit,
  spacing: Dimension = LocalSpacing.current
) {
  var welcomeState by remember {
    mutableStateOf(true)
  }

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
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    item {
      Text(
        text = stringResource(id = R.string.welcome_title_text_welcome),
        textAlign = TextAlign.Center,
        modifier = Modifier,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onBackground
      )

      Text(
        text = stringResource(id = R.string.welcome_text_terms_and_policy),
        textAlign = TextAlign.Center,
        modifier = Modifier
          .padding(top = 15.dp, start = 40.dp, end = 40.dp),
        style = MaterialTheme.typography.bodyLarge,
        color = HalfBlackColor
      )

      WelcomeSwitch(
        welcomeStateChange = {
          welcomeState = it
        },
        welcomeState = welcomeState
      )

      if (welcomeState) {
        Login(
          onLoginClick = {
            onEvent(WelcomeScreenUiEvent.OnLoginClick)
          }
        )
      } else {
        Register(
          onRegisterClick = {
            onEvent(WelcomeScreenUiEvent.OnRegisterClick)
          }
        )
      }

      Text(
        text = stringResource(id = R.string.welcome_text_or_connect_with),
        textAlign = TextAlign.Center,
        modifier = Modifier
          .padding(top = 15.dp),
        style = MaterialTheme.typography.bodyMedium,
      )

      Button(
        onClick = {},
        modifier = Modifier
          .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(Color.White)
      ) {
        Icon(
          painterResource(id = R.drawable.ic_google_logo),
          contentDescription = stringResource(id = R.string.welcome_google_icon_desc),
          tint = Color.Unspecified,
          modifier = Modifier.size(50.dp)
        )

        Text(
          text = stringResource(id = R.string.welcome_text_sign_in_with_google),
          Modifier.padding(start = 10.dp),
          color = Color.Gray,
          style = MaterialTheme.typography.titleMedium
        )
      }
    }
  }
}

@ExperimentalMaterial3Api
@Composable
@Preview(showBackground = true)
fun PreviewWelcomeScreen() {
  VegstyTheme {
    WelcomeScreen(
      uiEventFlow = Channel<UiEvent>().receiveAsFlow(),
      onNavigate = { _, _ -> },
      onEvent = {}
    )
  }
}


