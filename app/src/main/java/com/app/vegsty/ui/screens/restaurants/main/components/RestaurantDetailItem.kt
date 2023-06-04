package com.app.vegsty.ui.screens.restaurants.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.vegsty.data.remote.dto.Restaurant
import com.app.vegsty.ui.components.RecipeImage
import com.app.vegsty.ui.theme.DarkBackgroundColor
import com.app.vegsty.ui.theme.VegstyTheme

@Composable
fun RestaurantDetailItem(
  restaurant: Restaurant,
  onRestaurantClick: (Restaurant) -> Unit
) {
  Column(
    modifier = Modifier
      .background(
        color = MaterialTheme.colorScheme.secondary,
        shape = MaterialTheme.shapes.medium
      )
      .clickable(
        onClick = {
          onRestaurantClick(restaurant)
        }
      )
  ) {

    RecipeImage(
      imageUrl = restaurant.photo,
      onLoadImageAgainClick = {

      },
      shape = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp
      )
    )

    Text(
      text = restaurant.name,
      textAlign = TextAlign.Start,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp, vertical = 16.dp),
      style = MaterialTheme.typography.bodyLarge,
      color = DarkBackgroundColor
    )
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewRestaurantDetailItem() {
  VegstyTheme {
    RestaurantDetailItem(
      restaurant = Restaurant("", "Name", "detail1", "address", "14"),
      onRestaurantClick = {}
    )
  }
}