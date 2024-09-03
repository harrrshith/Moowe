package com.harrrshith.moowe.ui.navigation

import androidx.navigation.NavHostController

class MoweeNavigation(
    private val navController: NavHostController
) {
    val navigateToHome: () -> Unit = {
        navController.navigate(route = NavigationDestinations.Home) {
            launchSingleTop = true
            restoreState = false
        }
    }
}