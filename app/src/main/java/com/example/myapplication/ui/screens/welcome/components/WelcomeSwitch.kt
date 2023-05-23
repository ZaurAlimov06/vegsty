package com.example.myapplication.ui.screens.welcome.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.VegstyTheme

@Composable
fun WelcomeSwitch(
  welcomeStateChange: (Boolean) -> Unit,
  welcomeState: Boolean
) {

  Row(
    modifier = Modifier
      .padding(10.dp)
      .fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
  ) {
    Column {
      Text(
        text = stringResource(id = R.string.welcome_switch_button_login),
        textAlign = TextAlign.Center,
        modifier = Modifier
          .clickable(
            interactionSource = MutableInteractionSource(),
            indication = null,
            onClick = {
              welcomeStateChange(true)

            }
          ),
        style = MaterialTheme.typography.bodyMedium,
        color = if (welcomeState) {
          Color.Green
        } else {
          Color.Black
        },
      )

      Spacer(modifier = Modifier.height(2.dp))

      Spacer(
        modifier = Modifier
          .height(1.dp)
          .background(Color.Black)
      )
    }

    Spacer(modifier = Modifier.width(30.dp))

    Column {
      Text(
        text = stringResource(id = R.string.welcome_switch_button_register),
        textAlign = TextAlign.Center,
        modifier = Modifier
          .clickable(
            interactionSource = MutableInteractionSource(),
            indication = null,
            onClick = {
              welcomeStateChange(false)
            }
          ),
        style = MaterialTheme.typography.bodyMedium,
        color = if (welcomeState) {
          Color.Black
        } else {
          Color.Green
        }
      )

      Spacer(modifier = Modifier.height(2.dp))

      Spacer(
        modifier = Modifier
          .height(1.dp)
          .background(Color.Black)
      )
    }
  }
}

@Composable
@Preview(showBackground = true)
fun WelcomeSwitchPreview() {
  VegstyTheme {
    WelcomeSwitch(
      welcomeState = true,
      welcomeStateChange = {}
    )
  }
}