package com.harrrshith.moowe.ui.onboarding

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.harrrshith.moowe.R
import com.harrrshith.moowe.ui.theme.MooweTheme
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun OnBoardingRoute() {
    OnBoardingScreen(
        Modifier.safeDrawingPadding()
    )
}

@Composable
fun OnBoardingScreen(
    modifier: Modifier
) {
    var backgroundState by remember { mutableIntStateOf(1) }
    val imageList = listOf(
        R.drawable.image_one,
        R.drawable.image_two,
        R.drawable.image_four,
//        R.drawable.image_four,
//        R.drawable.image_five,
    )
    val gradientOne = Brush.linearGradient(
        colors = listOf(
            Color.Black,
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.tertiary,
            MaterialTheme.colorScheme.onPrimaryContainer
        )
    )

    val gradientTwo = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.onPrimaryContainer,
            Color.Black,
            MaterialTheme.colorScheme.tertiary,
        )
    )
    Crossfade(targetState = backgroundState, label = "", animationSpec = tween(500, easing = EaseInCubic)) {
        when(it) {
            1 -> {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(gradientOne)
                )
            }
            2 -> {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(gradientTwo)
                )
            }
        }

    }

    ArcList(
        modifier = modifier,
        radius = 270.dp,
    ) { index, rotationAngle ->
        Image(
            painter = painterResource(id = imageList[index]),
            contentDescription = "image $index",
            modifier = Modifier
                .rotate(rotationAngle)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
    }

    LaunchedEffect(key1 = backgroundState) {
        delay(4000)
        backgroundState = if(backgroundState == 1) 2 else 1
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ArcList(
    modifier: Modifier = Modifier,
    radius: Dp,
    itemSpacing: Dp = 60.dp,
    itemWidth: Dp = 100.dp,
    itemHeight: Dp = 160.dp,
    content: @Composable (Int, Float) -> Unit
) {
    BoxWithConstraints(modifier) {
        Layout(
            content = {
                for (i in 0 until 3) {  // Assuming 5 images, adjust as needed
                    val rotationAngle = when (i) {
                        0 -> -20f
                        1 -> 0f
                        2 -> 20f
                        else -> 0f
                    }
                    content(i, rotationAngle)
                }
            }
        ) { measurables, constraints ->
            val itemWidthPx = itemWidth.toPx().toInt()
            val itemHeightPx = itemHeight.toPx().toInt()
            val placeables = measurables.map { measurable ->
                measurable.measure(
                    constraints.copy(
                        minWidth = itemWidthPx,
                        maxWidth = itemWidthPx,
                        minHeight = itemHeightPx,
                        maxHeight = itemHeightPx
                    )
                )
            }

            val arcRadius = radius.toPx()
            val totalWidth = itemWidth.toPx() * placeables.size + itemSpacing.toPx() * (placeables.size - 1)
            val arcLength = PI.toFloat() * arcRadius
            val scale = totalWidth / arcLength

            layout(constraints.maxWidth, constraints.maxHeight) {
                val centerX = constraints.maxWidth / 2
                val centerY = constraints.maxHeight / 2
                placeables.forEachIndexed { index, placeable ->
                    val angleRad = (PI.toFloat() / 2) - (index - (placeables.size - 1) / 2f) * scale
                    val x = centerX + (arcRadius * cos(angleRad)).toInt() - placeable.width / 2
                    val y = (centerY - (arcRadius * sin(angleRad)).toInt() + placeable.height / 1.1).toInt()
                    placeable.place(x, y * 2)
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
            OnBoardingScreen(Modifier.fillMaxSize())
        }
    }
}
