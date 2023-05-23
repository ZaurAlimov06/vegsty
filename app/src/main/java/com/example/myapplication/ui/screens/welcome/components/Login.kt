package com.example.myapplication.ui.screens.welcome.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.VegstyTheme
import com.example.myapplication.ui.theme.WhiteColor

@ExperimentalMaterial3Api
@Composable
fun Login(
  onLoginClick: () -> Unit
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
        .fillMaxWidth()
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
      visualTransformation = PasswordVisualTransformation(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
        contentColor = WhiteColor
      ),
      shape = MaterialTheme.shapes.extraLarge
    ) {
      Text(
        text = stringResource(id = R.string.welcome_login_button_login),
        color = Color.White,
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
      onLoginClick = {}
    )
  }
}
