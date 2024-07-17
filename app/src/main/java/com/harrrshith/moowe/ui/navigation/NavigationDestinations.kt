package com.harrrshith.moowe.ui.navigation

object NavigationDestinations {

    data object Home : Screen("home") {
        data object Discover: Screen("discover")
        data object Explore: Screen("explore")
        data object Search: Screen("search")
        data object Profile: Screen("profile")
    }
    abstract class Screen(val route: String)
}