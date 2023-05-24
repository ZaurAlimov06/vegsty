package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.components.BottomBar
import com.example.myapplication.ui.route.NavigationType
import com.example.myapplication.ui.route.Route
import com.example.myapplication.ui.screens.favorites.FavoritesScreen
import com.example.myapplication.ui.screens.favorites.FavoritesViewModel
import com.example.myapplication.ui.screens.goals.main.GoalsScreen
import com.example.myapplication.ui.screens.goals.main.GoalsViewModel
import com.example.myapplication.ui.screens.homesearch.HomeSearchScreen
import com.example.myapplication.ui.screens.homesearch.HomeSearchViewModel
import com.example.myapplication.ui.screens.onboard.OnboardScreen
import com.example.myapplication.ui.screens.onboard.OnboardViewModel
import com.example.myapplication.ui.screens.profile.aboutus.AboutUsScreen
import com.example.myapplication.ui.screens.profile.main.ProfileScreen
import com.example.myapplication.ui.screens.profile.main.ProfileViewModel
import com.example.myapplication.ui.screens.restaurants.RestaurantScreen
import com.example.myapplication.ui.screens.restaurants.RestaurantViewModel
import com.example.myapplication.ui.screens.splash.SplashScreen
import com.example.myapplication.ui.screens.splash.SplashViewModel
import com.example.myapplication.ui.screens.welcome.WelcomeScreen
import com.example.myapplication.ui.screens.welcome.WelcomeViewModel
import com.example.myapplication.ui.theme.PrimaryColor
import com.example.myapplication.ui.theme.VegstyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  @ExperimentalMaterial3Api
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      BackHandler {
        if (isTaskRoot) {
          finish()
        }
      }

      val navController = rememberNavController()
      val backStackEntry by navController.currentBackStackEntryAsState()
      val currentScreenRoute: Route? = Route.getRoute(backStackEntry?.destination?.route)

      val view = LocalView.current
      SideEffect {
        val window = (view.context as Activity).window
        window.statusBarColor = PrimaryColor.toArgb()
      }

      VegstyTheme {
        Scaffold(
          modifier = Modifier
            .fillMaxSize(),
          bottomBar = {
            BottomBar(
              route = currentScreenRoute,
              onNavigate = { navType ->
                navController.handleNavigation(navType, null)
              }
            )
          }
        ) { paddingValues ->
          NavHost(
            modifier = Modifier
              .padding(paddingValues),
            navController = navController,
            startDestination = Route.SCREEN_SPLASH.name
          ) {
            composable(Route.SCREEN_SPLASH.name) {
              val splashViewModel: SplashViewModel = hiltViewModel()

              SplashScreen(
                uiEventFlow = splashViewModel.uiEvent,
                onNavigate = { navigationType, data ->
                  navController.handleNavigation(navigationType, data)
                }
              )
            }

            composable(Route.SCREEN_FIRST.name) {
              val onboardViewModel: OnboardViewModel = hiltViewModel()

              OnboardScreen(
                uiEventFlow = onboardViewModel.uiEvent,
                onNavigate = { navigationType, data ->
                  navController.handleNavigation(navigationType, data)
                },
                onEvent = {
                  onboardViewModel.onEvent(it)
                }
              )
            }

            composable(Route.SCREEN_WELCOME.name) {
              val welcomeViewModel: WelcomeViewModel = hiltViewModel()

              WelcomeScreen(
                uiEventFlow = welcomeViewModel.uiEvent,
                onNavigate = { navigationType, data ->
                  navController.handleNavigation(navigationType, data)
                },
                onEvent = {
                  welcomeViewModel.onEvent(it)
                }
              )
            }

            composable(Route.SCREEN_RESTAURANTS.name) {
              val restaurantViewModel: RestaurantViewModel = hiltViewModel()

              RestaurantScreen(
                uiEventFlow = restaurantViewModel.uiEvent,
                onNavigate = { navigationType, data ->
                  navController.handleNavigation(navigationType, data)
                }
              )
            }

            composable(Route.SCREEN_SEARCH.name) {
              val homeSearchViewModel: HomeSearchViewModel = hiltViewModel()

              HomeSearchScreen(
                uiStateFlow = homeSearchViewModel.uiState,
                uiEventFlow = homeSearchViewModel.uiEvent,
                onNavigate = { navigationType, data ->
                  navController.handleNavigation(navigationType, data)
                }
              )
            }

            composable(Route.SCREEN_GOALS.name) {
              val goalsViewModel: GoalsViewModel = hiltViewModel()

              GoalsScreen(
                uiEventFlow = goalsViewModel.uiEvent,
                onNavigate = { navigationType, data ->
                  navController.handleNavigation(navigationType, data)
                }
              )
            }

            composable(Route.SCREEN_FAVORITES.name) {
              val favoritesViewModel: FavoritesViewModel = hiltViewModel()

              FavoritesScreen(
                uiEventFlow = favoritesViewModel.uiEvent,
                onNavigate = { navigationType, data ->
                  navController.handleNavigation(navigationType, data)
                }
              )
            }

            composable(Route.SCREEN_PROFILE.name) {
              val profileViewModel: ProfileViewModel = hiltViewModel()

              ProfileScreen(
                uiEventFlow = profileViewModel.uiEvent,
                onNavigate = { navigationType, data ->
                  navController.handleNavigation(navigationType, data)
                },
                onEvent = {
                  profileViewModel.onEvent(it)
                }
              )
            }
            composable(Route.SCREEN_ABOUT_US.name) {

              AboutUsScreen(

              )
            }
          }
        }
      }
    }
  }

  fun NavHostController.handleNavigation(
    navigationType: NavigationType,
    data: Map<String, Any?>?
  ) {
    val route = when (navigationType) {
      is NavigationType.Navigate -> {
        navigationType.route
      }

      is NavigationType.ClearBackStackNavigate -> {
        navigationType.route
      }

      is NavigationType.BottomBarNavigate -> {
        navigationType.route
      }
    }

    navigate(
      route = route
    ) {
      when (navigationType) {
        is NavigationType.ClearBackStackNavigate -> {
          val popUpRoute = navigationType.popUpRoute
          if (popUpRoute != null) {
            popUpTo(popUpRoute)
          } else {
            popUpTo(0)
          }
        }

        is NavigationType.BottomBarNavigate -> {
          popUpTo(Route.SCREEN_RESTAURANTS.name) {
            saveState = true
          }
          launchSingleTop = true
          restoreState = true
        }

        else -> {}
      }
    }
    getBackStackEntry(route = route).apply {
      data?.forEach { (key, value) ->
        savedStateHandle.set(
          key = key,
          value = value
        )
      }
    }
  }
}

