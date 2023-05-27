package com.app.vegsty.ui.model

import androidx.annotation.DrawableRes
import com.app.vegsty.R
import com.app.vegsty.ui.route.Route

sealed class BottomBarType(
  @DrawableRes val icon: Int,
  val screenRoute: Route
) {
  object Search : BottomBarType(R.drawable.ic_bnv_search, Route.SCREEN_SEARCH)
  object Favorites : BottomBarType(R.drawable.ic_bnv_favorites, Route.SCREEN_FAVORITES)

  object Goals : BottomBarType(R.drawable.ic_bnv_goals, Route.SCREEN_GOALS)
  object Restaurants : BottomBarType(R.drawable.ic_bnv_restaurant, Route.SCREEN_RESTAURANTS)
  object Profile : BottomBarType(R.drawable.ic_bnv_profile, Route.SCREEN_PROFILE)
}

val bottomNavigationItems: List<BottomBarType> = listOf(
  BottomBarType.Search,
  BottomBarType.Favorites,
  BottomBarType.Goals,
  BottomBarType.Restaurants,
  BottomBarType.Profile
)
