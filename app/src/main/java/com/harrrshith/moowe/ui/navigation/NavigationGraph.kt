package com.harrrshith.moowe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.harrrshith.moowe.ui.ScreenFour
import com.harrrshith.moowe.ui.ScreenOne
import com.harrrshith.moowe.ui.ScreenThree
import com.harrrshith.moowe.ui.ScreenTwo
import com.harrrshith.moowe.ui.discover.DiscoverRoute
import com.harrrshith.moowe.ui.onboarding.OnBoardingRoute

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onTopBarTitleChange: (String) -> Unit
) {
    val startDestination = NavigationDestinations.Onboard
    val navigationActions = remember(navController) {
        MoweeNavigation(navController)
    }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable<NavigationDestinations.Onboard> {
            OnBoardingRoute(
                navigateToHome = { navigationActions.navigateToHome() }
            )
        }
        navigation<NavigationDestinations.Home>(
            startDestination = NavigationDestinations.Home.Discover,
        ){
            composable<NavigationDestinations.Home.Discover> {
                onTopBarTitleChange("Discover")
                DiscoverRoute()
            }
            composable<NavigationDestinations.Home.Explore> {
                onTopBarTitleChange("Explore")
                ScreenTwo()
            }
            composable<NavigationDestinations.Home.Search> {
                onTopBarTitleChange("Search")
                ScreenThree()
            }
            composable<NavigationDestinations.Home.Profile> {
                onTopBarTitleChange("Profile")
                ScreenFour()
            }
        }
    }
}