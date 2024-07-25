package com.harrrshith.moowe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.harrrshith.moowe.ui.ScreenFour
import com.harrrshith.moowe.ui.ScreenOne
import com.harrrshith.moowe.ui.ScreenThree
import com.harrrshith.moowe.ui.ScreenTwo
import com.harrrshith.moowe.ui.onboarding.OnBoardingRoute

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationDestinations.OnBoarding.route,
) {
    NavHost(navController = navController, startDestination = startDestination){
        composable(NavigationDestinations.OnBoarding.route){
            OnBoardingRoute()
        }
        navigation(
            route = NavigationDestinations.Home.route,
            startDestination = NavigationDestinations.Home.Discover.route
        ){
            composable(NavigationDestinations.Home.Discover.route){
                ScreenOne()
            }
            composable(NavigationDestinations.Home.Explore.route){
                ScreenTwo()
            }
            composable(NavigationDestinations.Home.Search.route){
                ScreenThree()
            }
            composable(NavigationDestinations.Home.Profile.route){
                ScreenFour()
            }

        }
    }
}