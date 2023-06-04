package com.app.vegsty.ui.screens.restaurants.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.vegsty.R
import com.app.vegsty.data.remote.dto.Restaurant
import com.app.vegsty.ui.components.RecipeImage
import com.app.vegsty.ui.theme.DarkBackgroundColor
import com.app.vegsty.ui.theme.Dimension
import com.app.vegsty.ui.theme.LocalSpacing
import com.app.vegsty.ui.theme.VegstyTheme

@Composable
fun RestaurantDetailScreen(
  restaurant: Restaurant?,
  spacing: Dimension = LocalSpacing.current
) {

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(
        horizontal = spacing.spaceScreenHorizontalPadding,
        vertical = spacing.spaceScreenVerticalPadding
      )
  ) {
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .weight(1.0f)
    ) {

      item {
        if (restaurant != null) {
          RecipeImage(
            imageUrl = restaurant.photo,
            onLoadImageAgainClick = {

            },
            shape = RoundedCornerShape(
              16.dp
            )
          )
        }

        if (restaurant != null) {
          Text(
            text = restaurant.name,
            textAlign = TextAlign.Start,
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 16.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = DarkBackgroundColor
          )
        }

        Text(
          text = stringResource(id = R.string.restaurant_detail_text_details),
          textAlign = TextAlign.Start,
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
          style = MaterialTheme.typography.bodyLarge,
          color = DarkBackgroundColor
        )

        if (restaurant != null) {
          Text(
            text = restaurant.detail,
            textAlign = TextAlign.Start,
            modifier = Modifier
              .fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium,
            color = DarkBackgroundColor
          )
        }

        Text(
          text = stringResource(id = R.string.restaurant_detail_text_address),
          textAlign = TextAlign.Start,
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
          style = MaterialTheme.typography.bodyLarge,
          color = DarkBackgroundColor
        )

        if (restaurant != null) {
          Text(
            text = restaurant.address,
            textAlign = TextAlign.Start,
            modifier = Modifier
              .fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium,
            color = DarkBackgroundColor
          )
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewRestaurantDetailScreen() {
  VegstyTheme {
    RestaurantDetailScreen(
      restaurant = Restaurant("", "Name", "detail1", "address", "14"),
    )
  }
}