package com.example.myapplication.ui.screens.welcome.components

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
fun Register(
  onRegisterClick: () -> Unit
) {
  var emailStateRegister by remember {
    mutableStateOf("")
  }
  var passwordStateRegister by remember {
    mutableStateOf("")
  }
  var passwordAgainStateRegister by remember {
    mutableStateOf("")
  }
  OutlinedTextField(
    value = emailStateRegister,
    label = { Text(stringResource(id = R.string.welcome_register_label_email_address)) },
    onValueChange = {
      emailStateRegister = it
    },
    modifier = Modifier
      .padding(top = 20.dp)
      .fillMaxWidth()
  )
  OutlinedTextField(
    value = passwordStateRegister,
    label = { Text(stringResource(id = R.string.welcome_register_label_password)) },
    onValueChange = {
      passwordStateRegister = it
    },
    modifier = Modifier
      .padding(top = 5.dp)
      .fillMaxWidth(),
    visualTransformation = PasswordVisualTransformation(),
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
  )
  OutlinedTextField(
    value = passwordAgainStateRegister,
    label = { Text(stringResource(id = R.string.welcome_register_label_password_again)) },
    onValueChange = {
      passwordAgainStateRegister = it
    },
    modifier = Modifier
      .padding(top = 5.dp)
      .fillMaxWidth(),
    visualTransformation = PasswordVisualTransformation(),
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
  )
  {
    Text(
      text = stringResource(id = R.string.welcome_register_button_register),
      color = Color.White, style = MaterialTheme.typography.titleLarge
    )
  }
}

@ExperimentalMaterial3Api
@Composable
@Preview(showBackground = true)
fun PreviewRegister() {
  VegstyTheme {
    Register(
      onRegisterClick = {}
    )
  }
}