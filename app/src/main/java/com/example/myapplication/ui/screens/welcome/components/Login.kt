package com.example.myapplication.ui.screens.welcome.components

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
import com.example.myapplication.R
import com.example.myapplication.ui.components.IconPasswordVisibility
import com.example.myapplication.ui.theme.VegstyTheme

@ExperimentalMaterial3Api
@Composable
fun Login(
  onLoginClick: () -> Unit,
  onPassToggleStateChange: (Boolean) -> Unit,
  passVisibilityState: Boolean
) {
  var emailStateLogin by remember {
    mutableStateOf("")
  }
  var passwordStateLogin by remember {
    mutableStateOf("")
  }
  Column {
    OutlinedTextField(
      value = emailStateLogin,
      label = { Text(stringResource(id = R.string.welcome_login_label_email_address)) },
      onValueChange = {
        emailStateLogin = it
      },
      modifier = Modifier
        .padding(top = 20.dp)
        .fillMaxWidth(),
      leadingIcon = {
        Icon(
          painter = painterResource(id = R.drawable.ic_mail),
          contentDescription = stringResource(id = R.string.common_icon_content_description),
          tint = MaterialTheme.colorScheme.onBackground
        )
      },
      colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.onBackground
      ),
      singleLine = true,
      maxLines = 1
    )

    OutlinedTextField(
      value = passwordStateLogin,
      label = { Text(stringResource(id = R.string.welcome_login_label_password)) },
      onValueChange = {
        passwordStateLogin = it
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
      colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.onBackground
      ),
      singleLine = true,
      maxLines = 1
    )

    Button(
      onClick = {
        onLoginClick()
      },
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp),
      colors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
      ),
      shape = MaterialTheme.shapes.extraLarge
    ) {
      Text(
        text = stringResource(id = R.string.welcome_login_button_login),
        color = MaterialTheme.colorScheme.onPrimary,
        style = MaterialTheme.typography.titleLarge
      )
    }
  }
}

@ExperimentalMaterial3Api
@Composable
@Preview(showBackground = true)
fun PreviewLogin() {
  VegstyTheme {
    Login(
      onLoginClick = {},
      onPassToggleStateChange = {},
      passVisibilityState = true
    )
  }
}
