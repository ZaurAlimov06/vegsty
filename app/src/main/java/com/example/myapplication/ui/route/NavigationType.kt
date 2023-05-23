package com.example.myapplication.ui.route

sealed class NavigationType {
  data class Navigate(val route: String) : NavigationType()
  data class ClearBackStackNavigate(val route: String, val popUpRoute: String? = null) : NavigationType()
  data class BottomBarNavigate(val route: String) : NavigationType()
}
