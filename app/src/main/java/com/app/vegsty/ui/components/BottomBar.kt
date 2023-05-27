package com.app.vegsty.ui.components

import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.vegsty.R
import com.app.vegsty.ui.model.bottomNavigationItems
import com.app.vegsty.ui.route.NavigationType
import com.app.vegsty.ui.route.Route
import com.app.vegsty.ui.theme.UnselectColor

@Composable
fun BottomBar(
  route: Route?,
  onNavigate: (NavigationType) -> Unit
) {

  if (route?.hasBottomBar == true) {
    NavigationBar(
      modifier = Modifier
        .background(
          color = MaterialTheme.colorScheme.background
        ),
      containerColor = Color.Transparent,
      contentColor = MaterialTheme.colorScheme.primary,
      tonalElevation = 0.dp
    ) {

      bottomNavigationItems.forEach { item ->
        NavigationBarItem(
          enabled = true,
          icon = {
            Icon(
              modifier = Modifier,
              painter = painterResource(id = item.icon),
              contentDescription = stringResource(id = R.string.common_icon_content_description),
              tint = if (item.screenRoute == Route.SCREEN_GOALS) {
                Color.Unspecified
              } else {
                UnselectColor
              }
            )
          },
          colors = NavigationBarItemDefaults.colors(
            selectedIconColor = if (item.screenRoute == Route.SCREEN_GOALS) {
              Color.Unspecified
            } else {
              MaterialTheme.colorScheme.primary
            },
            indicatorColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = UnselectColor,
          ),
          alwaysShowLabel = false,
          selected = route == item.screenRoute,
          onClick = {
            onNavigate(NavigationType.BottomBarNavigate(item.screenRoute.name))
          }
        )
      }
    }
  }
}