package com.harrrshith.moowe.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.harrrshith.moowe.ui.navigation.NavigationDestinations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MooweTopAppBar(
     navController: NavHostController,
     topBarTitle: String,
     onBackPressed: () -> Unit = {}
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    if (currentDestination != NavigationDestinations.Onboard::class.qualifiedName) {
        TopAppBar(
            title = { Text(text = topBarTitle, style = MaterialTheme.typography.titleLarge) },
            navigationIcon = {
                if (navController.previousBackStackEntry != null && shouldShowBottomShouldShowNavigationIcon(currentDestination)) {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.tertiary,
                navigationIconContentColor = MaterialTheme.colorScheme.tertiary
            )
        )
    }
}

val bottomBarDestinations = MooweBottomBarDestinations.entries.map { it.route }

private val shouldShowBottomShouldShowNavigationIcon: (currentRoute: String?) -> Boolean= { currentRoute ->
    currentRoute !in bottomBarDestinations.map { it::class.qualifiedName }
}