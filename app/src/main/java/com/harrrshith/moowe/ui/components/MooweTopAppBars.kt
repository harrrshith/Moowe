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
import com.harrrshith.moowe.ui.navigation.isTopLevelDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MooweTopAppBar(
     navController: NavHostController,
     topBarTitle: String,
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    if (isTopLevelDestination(currentDestination)) {
        TopAppBar(
            title = { Text(text = topBarTitle, style = MaterialTheme.typography.titleLarge) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.tertiary,
                navigationIconContentColor = MaterialTheme.colorScheme.tertiary
            )
        )
    }
}