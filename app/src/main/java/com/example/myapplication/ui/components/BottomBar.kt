package com.example.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.model.bottomNavigationItems
import com.example.myapplication.ui.route.NavigationType
import com.example.myapplication.ui.route.Route
import com.example.myapplication.ui.theme.SeparatorColor
import com.example.myapplication.ui.theme.UnselectColor

@Composable
fun BottomBar(
  route: Route?,
  onNavigate: (NavigationType) -> Unit
) {

  if (route?.hasBottomBar == true) {

    Column {
      Spacer(
        modifier = Modifier
          .fillMaxWidth()
          .height(1.dp)
          .background(SeparatorColor)
      )

      NavigationBar(
        modifier = Modifier
          .background(
            color = Color.White
          )
          .padding(top = 10.dp, bottom = 10.dp),
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
                contentDescription = "Item",
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
}