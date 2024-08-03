package com.harrrshith.moowe.ui.home

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.harrrshith.moowe.ui.navigation.NavigationDestinations

enum class HomeTabs(
    val route: NavigationDestinations,
    val screenName: String,
    val icon: ImageVector
){
    DISCOVER(
        route = NavigationDestinations.Home.Discover,
        screenName = "Discover",
        icon = Icons.Filled.Home
    ),
    EXPLORE(
        route = NavigationDestinations.Home.Explore,
        screenName = "Explore",
        icon = Icons.Filled.Favorite
    ),
    SEARCH(
        route = NavigationDestinations.Home.Search,
        screenName = "Search",
        icon = Icons.Filled.Search
    ),
    PROFILE(
        route = NavigationDestinations.Home.Profile,
        screenName = "Search",
        icon = Icons.Filled.Person
    )
}

@Composable
fun HomeBottomBar(navController: NavHostController){
    val tabs = remember { HomeTabs.entries.toTypedArray().asList() }
    val routes = remember { HomeTabs.entries.map { it.route } }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val shouldShowBottomBar = currentDestination?.route in routes.map { it::class.qualifiedName }
    if (shouldShowBottomBar) {
        HomeBottomBarView(
            tabs = tabs,
            currentRoute = currentDestination,
            tabClick = {
                if (it.route != currentDestination) {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )
    }
}

@Composable
private fun HomeBottomBarView(
    tabs: List<HomeTabs>,
    currentRoute: NavDestination?,
    tabClick: (HomeTabs) -> Unit
) {
    NavigationBar(
        Modifier.background(Color.Transparent),
        tonalElevation = 4.dp
    ) {
        tabs.forEach { tab ->
            val selected = currentRoute?.hierarchy?.any { it.route == tab.route::class.qualifiedName } == true
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.screenName,
                        tint = if (selected) MaterialTheme.colorScheme.primary else Color.Black
                    )
                },
                selected = selected,
                onClick = { tabClick(tab) },
                alwaysShowLabel = false,
                label = {
                    Text(text = tab.screenName, style = MaterialTheme.typography.bodySmall)
                }
            )
        }
    }
}