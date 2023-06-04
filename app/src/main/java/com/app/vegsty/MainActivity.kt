package com.app.vegsty

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.vegsty.data.remote.dto.Goal
import com.app.vegsty.data.remote.dto.Recipe
import com.app.vegsty.ui.components.BottomBar
import com.app.vegsty.ui.route.NavigationType
import com.app.vegsty.ui.route.Route
import com.app.vegsty.ui.route.RouteArgument
import com.app.vegsty.ui.screens.detail.DetailScreen
import com.app.vegsty.ui.screens.detail.DetailViewModel
import com.app.vegsty.ui.screens.favorites.FavoritesScreen
import com.app.vegsty.ui.screens.favorites.FavoritesViewModel
import com.app.vegsty.ui.screens.goals.detail.GoalDetailScreen
import com.app.vegsty.ui.screens.goals.insert.GoalInsertScreen
import com.app.vegsty.ui.screens.goals.insert.GoalInsertViewModel
import com.app.vegsty.ui.screens.goals.main.GoalsScreen
import com.app.vegsty.ui.screens.goals.main.GoalsViewModel
import com.app.vegsty.ui.screens.homesearch.HomeSearchScreen
import com.app.vegsty.ui.screens.homesearch.HomeSearchViewModel
import com.app.vegsty.ui.screens.onboard.OnboardScreen
import com.app.vegsty.ui.screens.onboard.OnboardViewModel
import com.app.vegsty.ui.screens.profile.aboutus.AboutUsScreen
import com.app.vegsty.ui.screens.profile.edit.EditProfile
import com.app.vegsty.ui.screens.profile.edit.EditProfileViewModel
import com.app.vegsty.ui.screens.profile.insert.DataInsertScreen
import com.app.vegsty.ui.screens.profile.insert.DataInsertViewModel
import com.app.vegsty.ui.screens.profile.main.ProfileScreen
import com.app.vegsty.ui.screens.profile.main.ProfileViewModel
import com.app.vegsty.ui.screens.profile.settings.SettingsScreen
import com.app.vegsty.ui.screens.profile.settings.SettingsViewModel
import com.app.vegsty.ui.screens.profile.terms.TermsScreen
import com.app.vegsty.ui.screens.restaurants.detail.FirstRestaurantScreen
import com.app.vegsty.ui.screens.restaurants.detail.SecondRestaurantScreen
import com.app.vegsty.ui.screens.restaurants.main.RestaurantScreen
import com.app.vegsty.ui.screens.restaurants.main.RestaurantViewModel
import com.app.vegsty.ui.screens.splash.SplashScreen
import com.app.vegsty.ui.screens.splash.SplashViewModel
import com.app.vegsty.ui.screens.welcome.WelcomeScreen
import com.app.vegsty.ui.screens.welcome.WelcomeViewModel
import com.app.vegsty.ui.theme.DarkBackgroundColor
import com.app.vegsty.ui.theme.PrimaryColor
import com.app.vegsty.ui.theme.VegstyTheme
import com.app.vegsty.ui.theme.WhiteColor
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
      val isLoading = remember {
        mutableStateOf(false)
      }

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

            composable(Route.SCREEN_ONBOARD.name) {
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

            composable(Route.SCREEN_WELCOME.name) { currentStackEntry ->
              val welcomeViewModel: WelcomeViewModel = hiltViewModel()

              WelcomeScreen(
                uiStateFlow = welcomeViewModel.uiState,
                uiEventFlow = welcomeViewModel.uiEvent,
                onNavigate = { navigationType, data ->
                  navController.handleNavigation(navigationType, data)
                },
                showShortToast = {
                  Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                },
                showLongToast = {
                  Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
                },
                updateLoading = {
                  isLoading.value = it
                },
                onEvent = {
                  welcomeViewModel.onEvent(it)
                },
                isLoginScreen = currentStackEntry.savedStateHandle.get<Boolean>(RouteArgument.ARG_WELCOME_IS_LOGIN_SCREEN.name) ?: true
              )
            }

            composable(Route.SCREEN_RESTAURANTS.name) {
              val restaurantViewModel: RestaurantViewModel = hiltViewModel()

              RestaurantScreen(
                uiEventFlow = restaurantViewModel.uiEvent,
                onNavigate = { navigationType, data ->
                  navController.handleNavigation(navigationType, data)
                },
                onEvent = {
                  restaurantViewModel.onEvent(it)
                }
              )
            }

            composable(Route.SCREEN_FIRST_RESTAURANT.name) {
              FirstRestaurantScreen()
            }

            composable(Route.SCREEN_SECOND_RESTAURANT.name) {
              SecondRestaurantScreen()
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
                },
                showShortToast = {
                  Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                },
                showLongToast = {
                  Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
                },
                updateLoading = {
                  isLoading.value = it
                }
              )
            }

            composable(Route.SCREEN_HOME_RECIPE_DETAIL.name) { currentStackEntry ->
              val detailViewModel: DetailViewModel = hiltViewModel()

              DetailScreen(
                uiEventFlow = detailViewModel.uiEvent,
                recipe = currentStackEntry.savedStateHandle.get<Recipe>(RouteArgument.ARG_HOME_RECIPE_DETAIL.name),
                isFromHome = currentStackEntry.savedStateHandle.get<Boolean>(RouteArgument.ARG_DETAIL_IS_FROM_HOME.name) ?: true,
                onEvent = {
                  detailViewModel.onEvent(it)
                },
                showShortToast = {
                  Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                },
                showLongToast = {
                  Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
                },
                updateLoading = {
                  isLoading.value = it
                }
              )
            }

            composable(Route.SCREEN_GOALS.name) {
              val goalsViewModel: GoalsViewModel = hiltViewModel()

              GoalsScreen(
                uiStateFlow = goalsViewModel.uiState,
                uiEventFlow = goalsViewModel.uiEvent,
                onNavigate = { navigationType, data ->
                  navController.handleNavigation(navigationType, data)
                },
                onEvent = {
                  goalsViewModel.onEvent(it)
                },
                showShortToast = {
                  Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                },
                showLongToast = {
                  Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
                },
                updateLoading = {
                  isLoading.value = it
                }
              )
            }

            composable(Route.SCREEN_FAVORITES.name) {
              val favoritesViewModel: FavoritesViewModel = hiltViewModel()

              FavoritesScreen(
                uiStateFlow = favoritesViewModel.uiState,
                uiEventFlow = favoritesViewModel.uiEvent,
                onNavigate = { navigationType, data ->
                  navController.handleNavigation(navigationType, data)
                },
                onEvent = {
                  favoritesViewModel.onEvent(it)
                }
              )
            }

            composable(Route.SCREEN_PROFILE.name) {
              val profileViewModel: ProfileViewModel = hiltViewModel()

              ProfileScreen(
                uiStateFlow = profileViewModel.uiState,
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

            composable(Route.SCREEN_EDIT_PROFILE.name) {
              val editProfileViewModel: EditProfileViewModel = hiltViewModel()

              EditProfile(
                uiStateFlow = editProfileViewModel.uiState,
                uiEventFlow = editProfileViewModel.uiEvent,
                showShortToast = {
                  Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                },
                showLongToast = {
                  Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
                },
                onEvent = {
                  editProfileViewModel.onEvent(it)
                },
                updateLoading = {
                  isLoading.value = it
                },
              )
            }

            composable(Route.SCREEN_TERMS.name) {
              TermsScreen()
            }

            composable(Route.SCREEN_DATA_INSERT.name) {
              val dataInsertViewModel: DataInsertViewModel = hiltViewModel()

              DataInsertScreen(
                uiStateFlow = dataInsertViewModel.uiState,
                uiEventFlow = dataInsertViewModel.uiEvent,
                onNavigate = { navigationType, data ->
                  navController.handleNavigation(navigationType, data)
                },
                showShortToast = {
                  Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                },
                showLongToast = {
                  Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
                },
                updateLoading = {
                  isLoading.value = it
                },
                onEvent = {
                  dataInsertViewModel.onEvent(it)
                }
              )
            }

            composable(Route.SCREEN_GOAL_ADD.name) {
              val goalsInsertViewModel: GoalInsertViewModel = hiltViewModel()

              GoalInsertScreen(
                uiStateFlow = goalsInsertViewModel.uiState,
                uiEventFlow = goalsInsertViewModel.uiEvent,
                onNavigate = { navigationType, data ->
                  navController.handleNavigation(navigationType, data)
                },
                showShortToast = {
                  Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                },
                showLongToast = {
                  Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
                },
                updateLoading = {
                  isLoading.value = it
                },
                onEvent = {
                  goalsInsertViewModel.onEvent(it)
                }
              )
            }

            composable(Route.SCREEN_GOAL_DETAIL.name) { currentStackEntry ->
              GoalDetailScreen(goal = currentStackEntry.savedStateHandle.get<Goal>(RouteArgument.ARG_GOAL_DETAIL.name))
            }
          }
        }

        if (isLoading.value) {
          Box(
            modifier = Modifier
              .fillMaxSize()
              .clickable(enabled = false, onClick = {})
          ) {
            CircularProgressIndicator(
              modifier = Modifier
                .align(Alignment.Center)
                .size(50.dp),
              color = MaterialTheme.colorScheme.tertiary
            )
          }
        }
      }
    }
  }

  private fun NavHostController.handleNavigation(
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
      is NavigationType.PopBack -> {
        navigationType.route
      }
    }

    when (navigationType) {
      is NavigationType.PopBack -> {
        previousBackStackEntry?.apply {
          data?.forEach { (key, value) ->
            savedStateHandle.set(
              key = key,
              value = value
            )
          }
        }
        popBackStack()

      }
      else -> {
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
  }
}

