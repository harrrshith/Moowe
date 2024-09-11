package com.harrrshith.moowe.ui.discover

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.harrrshith.moowe.R
import kotlin.random.Random

@Composable
fun DiscoverRoute(

){
    DiscoverScreen()
}

@Composable
private fun DiscoverScreen(

){
    val listOfImages = listOf(
        R.drawable.image_one,
        R.drawable.image_two,
        R.drawable.image_three,
        R.drawable.image_four,
        R.drawable.image_five,
    )
    val pagerState = rememberPagerState(
        pageCount = { listOfImages.size }
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        contentWindowInsets = WindowInsets(left = 0, right = 0),
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Cyan),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(state = pagerState) { imageIndex ->
                Image(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    painter = painterResource(id = listOfImages[imageIndex]),
                    contentDescription = "image",
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}