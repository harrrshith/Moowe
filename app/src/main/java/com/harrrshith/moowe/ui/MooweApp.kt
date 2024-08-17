package com.harrrshith.moowe.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.harrrshith.moowe.ui.components.MooweBottomBar
import com.harrrshith.moowe.ui.components.MooweTopAppBar
import com.harrrshith.moowe.ui.navigation.NavigationGraph
import com.harrrshith.moowe.ui.theme.MooweTheme

@Composable
fun MooweApp(
    modifier: Modifier = Modifier
){
    val navController = rememberNavController()
    var topBarTitle by remember { mutableStateOf("Discover") }
    MooweTheme {
        Scaffold(
            modifier = modifier.background(Color.Transparent),
            topBar = {
                MooweTopAppBar(
                    navController = navController,
                    topBarTitle = topBarTitle
                )
            },
            bottomBar = { MooweBottomBar(navController = navController)},
            contentWindowInsets = WindowInsets(0, 0, 0, 0)
        ) {innerPadding ->
            NavigationGraph(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                onTopBarTitleChange = { newTitle ->
                    topBarTitle = newTitle
                }
            )
        }
    }
}