package com.app.vegsty.ui.screens.profile.aboutus

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.vegsty.BuildConfig
import com.app.vegsty.R
import com.app.vegsty.ui.theme.Dimension
import com.app.vegsty.ui.theme.LocalSpacing
import com.app.vegsty.ui.theme.VegstyTheme

@Composable
fun AboutUsScreen(
  spacing: Dimension = LocalSpacing.current
) {
  Column(
    modifier = Modifier
      .padding(
        horizontal = spacing.spaceScreenHorizontalPadding,
        vertical = spacing.spaceScreenVerticalPadding
      )
  ) {
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .weight(1.0f),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {

      item {

        Icon(
          modifier = Modifier
            .padding(top = 50.dp),
          tint = Color.Unspecified,
          painter = painterResource(id = R.drawable.ic_logo_green),
          contentDescription = stringResource(id = R.string.common_icon_content_description)
        )

        Text(
          text = stringResource(id = R.string.about_us_text),
          textAlign = TextAlign.Center,
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
          style = MaterialTheme.typography.bodyMedium,
        )
      }
    }
    Text(
      text = "v${BuildConfig.VERSION_NAME}",
      textAlign = TextAlign.Center,
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp),
      style = MaterialTheme.typography.bodyMedium,
    )
  }
}

@Composable
@Preview(showBackground = true)
fun PreviewAboutUsScreen() {
  VegstyTheme {
    AboutUsScreen()
  }
}