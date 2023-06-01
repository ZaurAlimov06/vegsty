package com.app.vegsty.ui.screens.profile.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.vegsty.R
import com.app.vegsty.ui.components.IconSave
import com.app.vegsty.ui.model.UiEvent
import com.app.vegsty.ui.theme.Dimension
import com.app.vegsty.ui.theme.LocalSpacing
import com.app.vegsty.ui.theme.VegstyTheme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

@ExperimentalMaterial3Api
@Composable
fun EditProfile(
  uiStateFlow: StateFlow<EditProfileUiState>,
  uiEventFlow: Flow<UiEvent>,
  showShortToast: (String?) -> Unit,
  showLongToast: (String?) -> Unit,
  updateLoading: (Boolean) -> Unit,
  onEvent: (EditProfileUiEvent) -> Unit,
  spacing: Dimension = LocalSpacing.current
) {
  val uiState by uiStateFlow.collectAsState()

  LaunchedEffect(true) {
    uiEventFlow.collect { event ->
      when (event) {
        is UiEvent.ShowShortToast -> {
          showShortToast(event.message)
        }
        is UiEvent.ShowLongToast -> {
          showLongToast(event.message)
        }
        is UiEvent.ShowLoading -> {
          updateLoading(true)
        }
        is UiEvent.HideLoading -> {
          updateLoading(false)
        }
        else -> {}
      }
    }
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(
        horizontal = spacing.spaceScreenHorizontalPadding,
        vertical = spacing.spaceScreenVerticalPadding
      )
  ) {
    Text(
      text = stringResource(id = R.string.edit_profile_title_text_profile),
      textAlign = TextAlign.Center,
      modifier = Modifier
        .fillMaxWidth(),
      style = MaterialTheme.typography.bodySmall,
      color = MaterialTheme.colorScheme.onBackground
    )

    OutlinedTextField(
      value = uiState.userName,
      label = {
        Text(
          text = stringResource(id = R.string.welcome_register_label_username),
          style = MaterialTheme.typography.titleMedium
        )
      },
      onValueChange = {
        onEvent(EditProfileUiEvent.OnUpdateUsername(it))
      },
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp),
      leadingIcon = {
        Icon(
          painter = painterResource(id = R.drawable.ic_username),
          contentDescription = stringResource(id = R.string.common_icon_content_description),
          tint = MaterialTheme.colorScheme.onBackground
        )
      },
      trailingIcon = {
        IconSave(
          onClick = {
            onEvent(EditProfileUiEvent.OnUpdateRemoteUsername)
          }
        )
      },
      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done,
        capitalization = KeyboardCapitalization.Words
      ),
      singleLine = true,
      maxLines = 1,
      colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.onBackground
      ),
      textStyle = MaterialTheme.typography.titleMedium
    )
  }
}

@ExperimentalMaterial3Api
@Composable
@Preview(showBackground = true)
fun PreviewEditProfile() {
  VegstyTheme {
    EditProfile(
      uiStateFlow = MutableStateFlow(
        EditProfileUiState()
      ),
      uiEventFlow = Channel<UiEvent>().receiveAsFlow(),
      showShortToast = { },
      showLongToast = { },
      updateLoading = { },
      onEvent = {}
    )
  }
}
