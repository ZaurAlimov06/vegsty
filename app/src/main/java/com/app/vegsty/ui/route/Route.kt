package com.app.vegsty.ui.route

enum class Route(
  val hasBottomBar: Boolean = false
) {
  SCREEN_ABOUT_US,
  SCREEN_SPLASH,
  SCREEN_ONBOARD,
  SCREEN_WELCOME,
  SCREEN_SEARCH(hasBottomBar = true),
  SCREEN_FAVORITES(hasBottomBar = true),
  SCREEN_GOALS(hasBottomBar = true),
  SCREEN_RESTAURANTS(hasBottomBar = true),
  SCREEN_PROFILE(hasBottomBar = true),
  SCREEN_HOME_RECIPE_DETAIL,
  SCREEN_SETTINGS,
  SCREEN_EDIT_PROFILE,
  SCREEN_TERMS
  ;

  companion object {
    fun getRoute(routeName: String?): Route? {
      if (routeName != null) {
        values().forEach { route: Route ->
          if (routeName.equals(route.name, ignoreCase = true)) {
            return route
          }
        }
      }
      return null
    }
  }
}