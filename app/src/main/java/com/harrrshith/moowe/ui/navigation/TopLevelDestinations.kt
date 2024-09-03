package com.harrrshith.moowe.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class TopLevelDestination(val route: NavigationDestinations, val screenName: String, val icon: ImageVector) {
    data object DISCOVER: TopLevelDestination(route = NavigationDestinations.Home.Discover, screenName = "Discover", icon = Icons.Filled.Home)
    data object EXPLORE: TopLevelDestination(route = NavigationDestinations.Home.Explore, screenName = "Explore", icon = Icons.Filled.Favorite)
    data object SEARCH: TopLevelDestination(route = NavigationDestinations.Home.Search, screenName = "Search", icon = Icons.Filled.Search)
    data object PROFILE: TopLevelDestination(route = NavigationDestinations.Home.Profile, screenName = "Profile", icon = Icons.Filled.Person)
}

val topLevelDestinations: List<TopLevelDestination>
    get() = listOf(
        TopLevelDestination.DISCOVER,
        TopLevelDestination.EXPLORE,
        TopLevelDestination.SEARCH,
        TopLevelDestination.PROFILE
    )

val isTopLevelDestination: (currentRoute: String?) -> Boolean = {currentRoute ->
    currentRoute in topLevelDestinations.map { it.route::class.qualifiedName }
}