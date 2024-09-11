package com.harrrshith.moowe.ui

import android.annotation.SuppressLint
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
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MooweApp(
    modifier: Modifier = Modifier
){
    val navController = rememberNavController()
    MooweTheme {
        Scaffold(
            bottomBar = {
                MooweBottomBar(navController = navController)
            },
            contentWindowInsets = WindowInsets(0, 0, 0, -1000)
        ) {_ ->
            NavigationGraph(
                modifier = modifier,
                navController = navController
            )
        }
    }
}