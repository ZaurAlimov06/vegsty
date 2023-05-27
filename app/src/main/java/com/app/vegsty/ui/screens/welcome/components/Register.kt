package com.app.vegsty.ui.screens.welcome.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.vegsty.R
import com.app.vegsty.ui.components.IconPasswordVisibility
import com.app.vegsty.ui.theme.VegstyTheme
import com.app.vegsty.ui.theme.WhiteColor

@ExperimentalMaterial3Api
@Composable
fun Register(
  onRegisterClick: () -> Unit,
  onPassToggleStateChange: (Boolean) -> Unit,
  passVisibilityState: Boolean,
  onPassAgainToggleStateChange: (Boolean) -> Unit,
  passAgainVisibilityState: Boolean,
  onEmailValueChanged: (String) -> Unit,
  onPasswordValueChanged: (String) -> Unit
) {
  var usernameStateRegister by remember {
    mutableStateOf("")
  }
  var emailStateRegister by remember {
    mutableStateOf("")
  }
  var passwordStateRegister by remember {
    mutableStateOf("")
  }
  var passwordAgainStateRegister by remember {
    mutableStateOf("")
  }

  Column {
    OutlinedTextField(
      value = usernameStateRegister,
      label = {
        Text(
          text = stringResource(id = R.string.welcome_register_label_username),
          style = MaterialTheme.typography.titleMedium
        )
      },
      onValueChange = {
        usernameStateRegister = it
      },
      modifier = Modifier
        .padding(top = 20.dp)
        .fillMaxWidth(),
      leadingIcon = {
        Icon(
          painter = painterResource(id = R.drawable.ic_username),
          contentDescription = stringResource(id = R.string.common_icon_content_description),
          tint = MaterialTheme.colorScheme.onBackground
        )
      },
      singleLine = true,
      maxLines = 1,
      colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.onBackground
      ),
      textStyle = MaterialTheme.typography.titleMedium
    )

    OutlinedTextField(
      value = emailStateRegister,
      label = {
        Text(
          text = stringResource(id = R.string.welcome_register_label_email_address),
          style = MaterialTheme.typography.titleMedium
        )
      },
      onValueChange = {
        emailStateRegister = it
        onEmailValueChanged(it)
      },
      modifier = Modifier
        .padding(top = 5.dp)
        .fillMaxWidth(),
      leadingIcon = {
        Icon(
          painter = painterResource(id = R.drawable.ic_mail),
          contentDescription = stringResource(id = R.string.common_icon_content_description),
          tint = MaterialTheme.colorScheme.onBackground
        )
      },
      singleLine = true,
      maxLines = 1,
      colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.onBackground
      ),
      textStyle = MaterialTheme.typography.titleMedium
    )

    OutlinedTextField(
      value = passwordStateRegister,
      label = {
        Text(
          text = stringResource(id = R.string.welcome_register_label_password),
          style = MaterialTheme.typography.titleMedium
        )
      },
      onValueChange = {
        passwordStateRegister = it
        onPasswordValueChanged(it)
      },
      modifier = Modifier
        .padding(top = 5.dp)
        .fillMaxWidth(),
      visualTransformation = if (passVisibilityState) {
        VisualTransformation.None
      } else {
        PasswordVisualTransformation()
      },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
      leadingIcon = {
        Icon(
          painter = painterResource(id = R.drawable.ic_lock),
          contentDescription = stringResource(id = R.string.common_icon_content_description),
          tint = MaterialTheme.colorScheme.onBackground
        )
      },
      trailingIcon = {
        IconPasswordVisibility(
          onStateChange = {
            onPassToggleStateChange(it)
          }
        )
      },
      singleLine = true,
      maxLines = 1,
      colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.onBackground
      ),
      textStyle = MaterialTheme.typography.titleMedium
    )

    OutlinedTextField(
      value = passwordAgainStateRegister,
      label = {
        Text(
          text = stringResource(id = R.string.welcome_register_label_password_again),
          style = MaterialTheme.typography.titleMedium
        )
      },
      onValueChange = {
        passwordAgainStateRegister = it
      },
      modifier = Modifier
        .padding(top = 5.dp)
        .fillMaxWidth(),
      visualTransformation = if (passAgainVisibilityState) {
        VisualTransformation.None
      } else {
        PasswordVisualTransformation()
      },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
      leadingIcon = {
        Icon(
          painter = painterResource(id = R.drawable.ic_lock),
          contentDescription = stringResource(id = R.string.common_icon_content_description),
          tint = MaterialTheme.colorScheme.onBackground
        )
      },
      trailingIcon = {
        IconPasswordVisibility(
          onStateChange = {
            onPassAgainToggleStateChange(it)
          }
        )
      },
      singleLine = true,
      maxLines = 1,
      colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.onBackground
      ),
      textStyle = MaterialTheme.typography.titleMedium
    )

    Button(
      onClick = {
        onRegisterClick()
      },
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp),
      colors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = WhiteColor
      ),
      shape = MaterialTheme.shapes.extraLarge
    ) {
      Text(
        text = stringResource(id = R.string.welcome_register_button_register),
        color = MaterialTheme.colorScheme.onPrimary,
        style = MaterialTheme.typography.titleLarge
      )
    }
  }
}

@ExperimentalMaterial3Api
@Composable
@Preview(showBackground = true)
fun PreviewRegister() {
  VegstyTheme {
    Register(
      onRegisterClick = {},
      onPassToggleStateChange = { },
      onPassAgainToggleStateChange = { },
      passVisibilityState = true,
      passAgainVisibilityState = true,
      onEmailValueChanged = { },
      onPasswordValueChanged = { }
    )
  }
}