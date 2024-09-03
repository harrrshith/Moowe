package com.harrrshith.moowe.ui.components

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.harrrshith.moowe.ui.navigation.TopLevelDestination
import com.harrrshith.moowe.ui.navigation.isTopLevelDestination
import com.harrrshith.moowe.ui.navigation.topLevelDestinations

@Composable
fun MooweBottomBar(navController: NavHostController){
    val tabs = remember { topLevelDestinations }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    if (isTopLevelDestination(currentDestination)) {
        MooweBottomBar(
            tabs = tabs,
            currentRoute = currentDestination,
            tabClick = {
                if (it.route::class.qualifiedName != currentDestination) {
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
    tabs: List<TopLevelDestination>,
    currentRoute: String?,
    tabClick: (TopLevelDestination) -> Unit
) {
    NavigationBar(
        tonalElevation = 4.dp,
        containerColor = Color.Transparent,
        contentColor = Color.Transparent
    ) {
        tabs.forEach { tab ->
            val selected = currentRoute == tab.route::class.qualifiedName
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.screenName,
                        tint = if (selected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                selected = selected,
                onClick = { tabClick(tab) },
                alwaysShowLabel = true,
                label = {
                    Text(
                        text = tab.screenName,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal),
                        color = if (selected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onPrimaryContainer
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