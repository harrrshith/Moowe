package com.harrrshith.moowe.ui.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.harrrshith.moowe.ui.navigation.NavigationDestinations

enum class HomeTab(
    val route: String,
    val screenName: String,
    val icon: ImageVector
){
    Discover(
        route = NavigationDestinations.Home.Discover.route,
        screenName = "Discover",
        icon = Icons.Filled.Home
    ),
    Explore(
        route = NavigationDestinations.Home.Explore.route,
        screenName = "Explore",
        icon = Icons.Filled.Favorite
    ),
    SEARCH(
        route = NavigationDestinations.Home.Search.route,
        screenName = "Search",
        icon = Icons.Filled.Search
    ),
    PROFILE(
        route = NavigationDestinations.Home.Profile.route,
        screenName = "Search",
        icon = Icons.Filled.Person
    )
}

@Composable
fun HomeBottomBar(navController: NavHostController){
    val tabs = remember { HomeTab.entries.toTypedArray().asList() }
    val routes = remember { HomeTab.entries.map { it.route } }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route ?: NavigationDestinations.Home.Discover.route

    HomeBottomBarView(tabs = tabs, routes = routes, currentRoute = currentDestination, tabClick = {
        if (it.route != currentDestination) {
            navController.navigate(it.route) {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    })
}

@Composable
private fun HomeBottomBarView(
    tabs: List<HomeTab>,
    routes: List<String>,
    currentRoute: String,
    tabClick: (HomeTab) -> Unit
) {

    if (currentRoute in routes) {
        NavigationBar(
            Modifier
                .windowInsetsBottomHeight(
                    WindowInsets.navigationBars.add(WindowInsets(bottom = 56.dp))
                ),
            tonalElevation = 4.dp
        ) {
            tabs.forEach { tab ->
                val selected = currentRoute == tab.route
                NavigationBarItem(
                    icon = {
                           Icon(imageVector = tab.icon, contentDescription = tab.screenName, tint = if (selected) MaterialTheme.colorScheme.primary else Color.Black)
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
}