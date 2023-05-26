package com.example.myapplication.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.myapplication.R
import com.example.myapplication.ui.model.UiEvent
import com.example.myapplication.ui.route.NavigationType
import com.example.myapplication.ui.theme.PrimaryColor
import com.example.myapplication.ui.theme.VegstyTheme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun SplashScreen(
  uiEventFlow: Flow<UiEvent>,
  onNavigate: (NavigationType, data: Map<String, Any?>?) -> Unit,
  onChangeTheme: (Boolean) -> Unit,
) {
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

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(PrimaryColor)
  ) {
    Icon(
      modifier = Modifier
        .align(Alignment.Center),
      tint = Color.Unspecified,
      painter = painterResource(id = R.drawable.ic_logo_white),
      contentDescription = stringResource(id = R.string.common_icon_content_description)
    )
    Text(
      text = "Healtier, Tastier, Smarter",
      textAlign = TextAlign.Center,
      modifier = Modifier
        .fillMaxWidth()
        .align(Alignment.BottomCenter)
        .padding(bottom = 100.dp),
      style = MaterialTheme.typography.titleLarge,
      color = Color.White

    )

  }
}

@Composable
@Preview(showBackground = true)
fun PreviewSplashScreen() {
  VegstyTheme {
    SplashScreen(
      uiEventFlow = Channel<UiEvent>().receiveAsFlow(),
      onNavigate = { _, _ -> },
      onChangeTheme = { },
    )
  }
}