package com.harrrshith.moowe.ui.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.harrrshith.moowe.R
import com.harrrshith.moowe.ui.components.MooweTopAppBar

@Composable
fun DiscoverRoute(

){
    DiscoverScreen()
}

@Composable
internal fun DiscoverScreen(

){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        contentWindowInsets = WindowInsets(left = 0, right = 0),
        topBar = {
            MooweTopAppBar(topBarTitle = stringResource(id = R.string.discover))
        }
    ){innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(color = MaterialTheme.colorScheme.primary)
        ){
            Text(text = "Discover")
        }
    }
}