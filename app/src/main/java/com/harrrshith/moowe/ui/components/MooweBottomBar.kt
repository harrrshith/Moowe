package com.harrrshith.moowe.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.harrrshith.moowe.ui.navigation.NavigationDestinations

@Composable
fun MooweBottomBar(navController: NavHostController){
    val tabs = remember { MooweBottomBarDestinations.entries.toTypedArray().asList() }
    val routes = remember { MooweBottomBarDestinations.entries.map { it.route } }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val shouldShowBottomBar = currentDestination?.route in routes.map { it::class.qualifiedName }
    if (shouldShowBottomBar) {
        MooweBottomBar(
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
internal fun MooweBottomBar(
    tabs: List<MooweBottomBarDestinations>,
    currentRoute: NavDestination?,
    tabClick: (MooweBottomBarDestinations) -> Unit
) {
    NavigationBar(
        tonalElevation = 4.dp,
        containerColor = Color.Transparent,
        contentColor = Color.Transparent
    ) {
        tabs.forEach { tab ->
            val selected = currentRoute?.hierarchy?.any { it.route == tab.route::class.qualifiedName } == true
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.screen,
                        tint = if (selected) MaterialTheme.colorScheme.tertiary else Color.White
                    )
                },
                selected = selected,
                onClick = { tabClick(tab) },
                alwaysShowLabel = true,
                label = {
                    Text(
                        text = tab.screen,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal),
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = MaterialTheme.colorScheme.tertiary,
                    selectedIconColor = MaterialTheme.colorScheme.tertiary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}


enum class MooweBottomBarDestinations(
    val route: NavigationDestinations,
    val screen: String,
    val icon: ImageVector
){
    DISCOVER(route = NavigationDestinations.Home.Discover, screen = "Discover", icon = Icons.Filled.Home),
    EXPLORE(route = NavigationDestinations.Home.Explore, screen = "Explore", icon = Icons.Filled.Favorite),
    SEARCH(route = NavigationDestinations.Home.Search, screen = "Search", icon = Icons.Filled.Search),
    PROFILE(route = NavigationDestinations.Home.Profile, screen = "Search", icon = Icons.Filled.Person)
}