package com.harrrshith.moowe.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.harrrshith.moowe.R
import com.harrrshith.moowe.ui.theme.MooweTheme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ScreenOne(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "Screen One", style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun ScreenTwo(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "Screen Two", style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun ScreenThree(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "Screen Three", style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun ScreenFour(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "Screen Four", style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun OnBoardingScreen(
    navigateToHome: () -> Unit
){
    val imageList = listOf(
        R.drawable.image_two,
        R.drawable.image_one,
        R.drawable.image_two,
        R.drawable.image_one,
        R.drawable.image_two,
    )
    val gradientOne = Brush.linearGradient(
        colors = listOf(
            Color.Black,
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.tertiary,
            MaterialTheme.colorScheme.onPrimaryContainer,

        ),
        start = Offset(x = 1000f, y = 10f),
        end = Offset(x = 0f, y = 1000f)
    )
    ArcList(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientOne, alpha = .8f),
        radius = 180.dp,
    ) { index, rotationAngle ->
        Image(
            painter = painterResource(id = imageList[index]),
            contentDescription = "image $index",
            modifier = Modifier
                .height(120.dp)
                .aspectRatio(.7f)
                .rotate(rotationAngle)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ArcList(
    modifier: Modifier = Modifier,
    radius: Dp,
    itemSpacing: Dp = (-10).dp,
    content: @Composable (Int, Float) -> Unit
) {
    BoxWithConstraints(modifier) {
        Layout(
            content = {
                for (i in 0 until 5) {  // Assuming 5 images, adjust as needed
                    val rotationAngle = when (i) {
                        0 -> -20f
                        1 -> -18f
                        2 -> 0f
                        3 -> 18f
                        4 -> 20f
                        else -> 0f
                    }
                    content(i, rotationAngle)
                }
            }
        ) { measurables, constraints ->
            val placeables = measurables.map { measurable ->
                measurable.measure(constraints)
            }

            val arcRadius = radius.toPx()
            val itemWidth = placeables.first().width
            val totalWidth = itemWidth * placeables.size + itemSpacing.toPx() * (placeables.size - 1)
            val arcLength = PI.toFloat() * arcRadius
            val scale = totalWidth / arcLength

            layout(constraints.maxWidth, constraints.maxHeight) {
                val centerX = constraints.maxWidth / 2
                val centerY = constraints.maxHeight / 2
                placeables.forEachIndexed { index, placeable ->
                    val angleRad = (PI.toFloat() / 2) - (index - (placeables.size - 1) / 2f) * scale
                    val x = centerX + (arcRadius * cos(angleRad)).toInt() - placeable.width / 2
                    val y = centerY - (arcRadius * sin(angleRad)).toInt() + placeable.height * 2

                    placeable.place(x, y)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewOnBoardingScreen(){
    MooweTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            OnBoardingScreen {}
        }
    }
}