package com.harrrshith.moowe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.harrrshith.moowe.ui.ScreenFour
import com.harrrshith.moowe.ui.ScreenThree
import com.harrrshith.moowe.ui.ScreenTwo
import com.harrrshith.moowe.ui.discover.DiscoverRoute
import com.harrrshith.moowe.ui.onboarding.OnBoardingRoute

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
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
                DiscoverRoute()
            }
            composable<NavigationDestinations.Home.Explore> {
                ScreenTwo()
            }
            composable<NavigationDestinations.Home.Search> {
                ScreenThree()
            }
            composable<NavigationDestinations.Home.Profile> {
                ScreenFour()
            }
        }
    }
}