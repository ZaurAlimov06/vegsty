package com.app.vegsty.ui.screens.profile.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
import com.app.vegsty.ui.theme.*
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
      Text(
        text = stringResource(id = R.string.profile_title_text_profile),
        textAlign = TextAlign.Center,
        modifier = Modifier
          .fillMaxWidth(),
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onBackground
      )

      Icon(
        modifier = Modifier
          .padding(top = 16.dp),
        tint = MaterialTheme.colorScheme.onBackground,
        painter = painterResource(id = R.drawable.ic_profile),
        contentDescription = stringResource(id = R.string.common_icon_content_description)
      )

      Text(
        text = "User Name",
        textAlign = TextAlign.Center,
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 20.dp),
        style = MaterialTheme.typography.titleMedium,
      )

      Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 16.dp)
          .clickable(
            interactionSource = remember {
              MutableInteractionSource()
            },
            indication = rememberRipple(
              color = PrimaryColor
            ),
            onClick = {
              onEvent(ProfileUiEvent.OnEditProfileClick)
            }
          ),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          painterResource(id = R.drawable.ic_edit_profile),
          contentDescription = stringResource(id = R.string.common_icon_content_description),
          tint = Color.Unspecified
        )

        Text(
          text = stringResource(id = R.string.edit_profile_button),
          modifier = Modifier
            .padding(start = 16.dp)
            .weight(1.0f),
          color = MaterialTheme.colorScheme.onBackground,
          style = MaterialTheme.typography.titleMedium
        )

        Icon(
          painterResource(id = R.drawable.ic_arrow_right),
          contentDescription = stringResource(id = R.string.common_icon_content_description),
          tint = MaterialTheme.colorScheme.onBackground
        )
      }

      Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 16.dp)
          .clickable(
            interactionSource = remember {
              MutableInteractionSource()
            },
            indication = rememberRipple(
              color = PrimaryColor
            ),
            onClick = {
              onEvent(ProfileUiEvent.OnSettingsClick)
            }
          ),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          painterResource(id = R.drawable.ic_settings),
          contentDescription = stringResource(id = R.string.common_icon_content_description),
          tint = Color.Unspecified
        )

        Text(
          text = stringResource(id = R.string.settings),
          modifier = Modifier
            .padding(start = 10.dp)
            .weight(1.0f),
          color = MaterialTheme.colorScheme.onBackground,
          style = MaterialTheme.typography.titleMedium
        )

        Icon(
          painterResource(id = R.drawable.ic_arrow_right),
          contentDescription = stringResource(id = R.string.common_icon_content_description),
          tint = MaterialTheme.colorScheme.onBackground
        )
      }

      Spacer(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 16.dp)
          .height(1.dp)
          .background(SeparatorColor)
      )

      Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 16.dp)
          .clickable(
            interactionSource = remember {
              MutableInteractionSource()
            },
            indication = rememberRipple(
              color = PrimaryColor
            ),
            onClick = {
              onEvent(ProfileUiEvent.OnAboutUsClick)
            }
          ),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          painterResource(id = R.drawable.ic_about_us),
          contentDescription = stringResource(id = R.string.common_icon_content_description),
          tint = Color.Unspecified
        )

        Text(
          text = stringResource(id = R.string.about_us),
          modifier = Modifier
            .padding(start = 10.dp)
            .weight(1.0f),
          color = MaterialTheme.colorScheme.onBackground,
          style = MaterialTheme.typography.titleMedium
        )

        Icon(
          painterResource(id = R.drawable.ic_arrow_right),
          contentDescription = stringResource(id = R.string.common_icon_content_description),
          tint = MaterialTheme.colorScheme.onBackground
        )
      }

      Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 16.dp)
          .clickable(
            interactionSource = remember {
              MutableInteractionSource()
            },
            indication = rememberRipple(
              color = PrimaryColor
            ),
            onClick = {
              onEvent(ProfileUiEvent.OnTermsClick)
            }
          ),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          painterResource(id = R.drawable.ic_terms),
          contentDescription = stringResource(id = R.string.common_icon_content_description),
          tint = Color.Unspecified
        )

        Text(
          text = stringResource(id = R.string.terms_and_privacy_policy),
          modifier = Modifier
            .padding(start = 10.dp)
            .weight(1.0f),
          color = MaterialTheme.colorScheme.onBackground,
          style = MaterialTheme.typography.titleMedium
        )

        Icon(
          painterResource(id = R.drawable.ic_arrow_right),
          contentDescription = stringResource(id = R.string.common_icon_content_description),
          tint = MaterialTheme.colorScheme.onBackground
        )
      }

      Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 16.dp)
          .clickable(
            interactionSource = remember {
              MutableInteractionSource()
            },
            indication = rememberRipple(
              color = PrimaryColor
            ),
            onClick = {
              onEvent(ProfileUiEvent.OnLogOutClick)
            }
          ),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          painterResource(id = R.drawable.ic_log_out),
          contentDescription = stringResource(id = R.string.common_icon_content_description),
          tint = Color.Unspecified
        )

        Text(
          text = stringResource(id = R.string.log_out),
          modifier = Modifier
            .padding(start = 10.dp)
            .weight(1.0f),
          color = MaterialTheme.colorScheme.onBackground,
          style = MaterialTheme.typography.titleMedium
        )

        Icon(
          painterResource(id = R.drawable.ic_arrow_right),
          contentDescription = stringResource(id = R.string.common_icon_content_description),
          tint = MaterialTheme.colorScheme.onBackground
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

