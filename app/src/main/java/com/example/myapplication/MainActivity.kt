package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.remote.dto.Recipe
import com.example.myapplication.ui.components.BottomBar
import com.example.myapplication.ui.route.NavigationType
import com.example.myapplication.ui.route.Route
import com.example.myapplication.ui.route.RouteArgument
import com.example.myapplication.ui.screens.detail.DetailScreen
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
import com.example.myapplication.ui.screens.profile.settings.SettingsScreen
import com.example.myapplication.ui.screens.profile.settings.SettingsViewModel
import com.example.myapplication.ui.screens.restaurants.RestaurantScreen
import com.example.myapplication.ui.screens.restaurants.RestaurantViewModel
import com.example.myapplication.ui.screens.splash.SplashScreen
import com.example.myapplication.ui.screens.splash.SplashViewModel
import com.example.myapplication.ui.screens.welcome.WelcomeScreen
import com.example.myapplication.ui.screens.welcome.WelcomeViewModel
import com.example.myapplication.ui.theme.DarkBackgroundColor
import com.example.myapplication.ui.theme.PrimaryColor
import com.example.myapplication.ui.theme.VegstyTheme
import com.example.myapplication.ui.theme.WhiteColor
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

      val isThemeDark = remember {
        mutableStateOf(false)
      }

      val statusBarColor = remember {
        mutableStateOf(PrimaryColor)
      }

      val view = LocalView.current

      SideEffect {
        (view.context as Activity).window.apply {
          window.statusBarColor = statusBarColor.value.toArgb()
          WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = !isThemeDark.value
        }
      }

      VegstyTheme(
        darkTheme = isThemeDark.value
      ) {
        Scaffold(
          modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
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
                },
                onChangeTheme = {
                  isThemeDark.value = it
                  statusBarColor.value = if (it) {
                    DarkBackgroundColor
                  } else {
                    WhiteColor
                  }
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
                uiStateFlow = welcomeViewModel.uiState,
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
                },
                onEvent = {
                  homeSearchViewModel.onEvent(it)
                }
              )
            }

            composable(Route.SCREEN_HOME_RECIPE_DETAIL.name) { currentStackEntry ->

              DetailScreen(
                recipe = currentStackEntry.savedStateHandle.get<Recipe>(RouteArgument.ARG_HOME_RECIPE_DETAIL.name),
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
              AboutUsScreen()
            }

            composable(Route.SCREEN_SETTINGS.name) {
              val settingsViewModel: SettingsViewModel = hiltViewModel()

              SettingsScreen(
                uiStateFlow = settingsViewModel.uiState,
                uiEventFlow = settingsViewModel.uiEvent,
                onNavigate = { navigationType, data ->
                  navController.handleNavigation(navigationType, data)
                },
                onChangeTheme = {
                  isThemeDark.value = it
                  statusBarColor.value = if (it) {
                    DarkBackgroundColor
                  } else {
                    WhiteColor
                  }
                },
                onEvent = {
                  settingsViewModel.onEvent(it)
                }
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
          popUpTo(Route.SCREEN_SEARCH.name) {
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

