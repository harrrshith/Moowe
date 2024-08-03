package com.harrrshith.moowe.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.harrrshith.moowe.ui.home.HomeBottomBar
import com.harrrshith.moowe.ui.navigation.NavigationGraph
import com.harrrshith.moowe.ui.theme.MooweTheme

@Composable
fun MooweApp(
    modifier: Modifier = Modifier
){
    val navController = rememberNavController()
    MooweTheme {
        Scaffold(
            modifier = modifier,
            bottomBar = { HomeBottomBar(navController) }
        ) {innerPadding ->
            NavigationGraph(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
            )
        }
    }
}