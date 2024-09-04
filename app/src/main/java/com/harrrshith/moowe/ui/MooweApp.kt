package com.harrrshith.moowe.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.harrrshith.moowe.ui.components.MooweBottomBar
import com.harrrshith.moowe.ui.navigation.NavigationGraph
import com.harrrshith.moowe.ui.theme.MooweTheme

@Composable
fun MooweApp(
    modifier: Modifier = Modifier
){
    val navController = rememberNavController()
    MooweTheme {
        Scaffold(
            modifier = modifier.background(Color.Transparent),
            bottomBar = {
                MooweBottomBar(navController = navController)
            },
            contentWindowInsets = WindowInsets(0, 0, 0, 0)
        ) {innerPadding ->
            NavigationGraph(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
    }
}