package com.example.myapplication.ui.screens.profile.main

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
import com.example.myapplication.R
import com.example.myapplication.ui.model.UiEvent
import com.example.myapplication.ui.route.NavigationType
import com.example.myapplication.ui.theme.Dimension
import com.example.myapplication.ui.theme.LocalSpacing
import com.example.myapplication.ui.theme.PrimaryColor
import com.example.myapplication.ui.theme.VegstyTheme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun ProfileScreen(
  uiEventFlow: Flow<UiEvent>,
  onNavigate: (NavigationType, data: Map<String, Any?>?) -> Unit,
  onEvent: (ProfileUiEvent) -> Unit,
  spacing: Dimension = LocalSpacing.current

) {
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

        text = stringResource(id = R.string.profile_title_text_profile),
        textAlign = TextAlign.Center,
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 50.dp),
        style = MaterialTheme.typography.bodyLarge
      )
      Icon(
        modifier = Modifier
          .padding(top = 50.dp),

        tint = Color.Unspecified,
        painter = painterResource(id = R.drawable.ic_profilepicture),
        contentDescription = stringResource(id = R.string.onboard_logo_icon_content_desc)
      )
      Text(

        text = "User Name",
        textAlign = TextAlign.Center,
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 25.dp),
        style = MaterialTheme.typography.titleMedium,
      )
      Button(
        onClick = {},
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 30.dp),
        colors = ButtonDefaults.buttonColors(PrimaryColor),
      ) {
        Row(
          horizontalArrangement = Arrangement.Start,
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            painterResource(id = R.drawable.ic_bnv_profile),
            contentDescription = stringResource(id = R.string.edit_profile_button),
            tint = Color.White,
            modifier = Modifier.size(30.dp)
          )
          Text(
            text = stringResource(id = R.string.edit_profile_button),
            Modifier.padding(start = 10.dp),
            color = Color.White,
            style = MaterialTheme.typography.titleMedium
          )
        }
      }
      Button(
        onClick = {},
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 15.dp),
        colors = ButtonDefaults.buttonColors(PrimaryColor)
      ) {
        Icon(
          painterResource(id = R.drawable.ic_settings),
          contentDescription = stringResource(id = R.string.settings),
          tint = Color.White,
          modifier = Modifier.size(30.dp)
        )
        Text(
          text = stringResource(id = R.string.settings),
          Modifier.padding(start = 10.dp),
          color = Color.White,
          style = MaterialTheme.typography.titleMedium
        )
      }
      Button(
        onClick = {},
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 15.dp),
        colors = ButtonDefaults.buttonColors(PrimaryColor)
      ) {
        Icon(
          painterResource(id = R.drawable.ic_terms),
          contentDescription = stringResource(id = R.string.terms_and_privacy_policy),
          tint = Color.White,
          modifier = Modifier.size(30.dp)
        )
        Text(
          text = stringResource(id = R.string.terms_and_privacy_policy),
          Modifier.padding(start = 10.dp),
          color = Color.White,
          style = MaterialTheme.typography.titleMedium
        )
      }
      Button(
        onClick = {
          onEvent(ProfileUiEvent.OnAboutUsClick)
        },
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 15.dp),
        colors = ButtonDefaults.buttonColors(PrimaryColor)
      ) {
        Icon(
          painterResource(id = R.drawable.ic_about),
          contentDescription = stringResource(id = R.string.about_us),
          tint = Color.White,
          modifier = Modifier.size(30.dp)

        )
        Text(
          text = stringResource(id = R.string.about_us),
          Modifier.padding(start = 10.dp),
          color = Color.White,
          style = MaterialTheme.typography.titleMedium
        )
      }
      Button(
        onClick = {},
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 15.dp),
        colors = ButtonDefaults.buttonColors(PrimaryColor)
      ) {
        Icon(
          painterResource(id = R.drawable.ic_logout),
          contentDescription = stringResource(id = R.string.log_out),
          tint = Color.White,
          modifier = Modifier.size(30.dp)
        )
        Text(
          text = stringResource(id = R.string.log_out),
          Modifier.padding(start = 10.dp),
          color = Color.White,
          style = MaterialTheme.typography.titleMedium
        )
      }
    }
  }
}


@Composable
@Preview(showBackground = true)
fun PreviewProfileScreen() {
  VegstyTheme {
    ProfileScreen(
      uiEventFlow = Channel<UiEvent>().receiveAsFlow(),
      onNavigate = { _, _ -> },
      onEvent = {}
    )
  }
}

