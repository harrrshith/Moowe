package com.harrrshith.moowe.ui.onboarding

import android.annotation.SuppressLint
import android.graphics.Color.alpha
import android.util.Log
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.harrrshith.moowe.R
import com.harrrshith.moowe.ui.theme.MooweTheme
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin
import kotlin.random.Random

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
        R.drawable.image_four,
        R.drawable.image_five,
        R.drawable.image_one,
        R.drawable.image_two,
        R.drawable.image_four,
        R.drawable.image_four,
        R.drawable.image_five,
    )
//    val infiniteTransition = rememberInfiniteTransition(label = "infinite animation")
//    val animatedFloatAlpha by infiniteTransition.animateFloat(
//        initialValue = 0f,
//        targetValue = 1f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(durationMillis = 5000, easing = LinearEasing),
//            repeatMode = RepeatMode.Reverse
//        ),
//        label = "alpha"
//    )
//
//    val animatedFloatAlphaReverse by infiniteTransition.animateFloat(
//        initialValue = 1f,
//        targetValue = 0f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(durationMillis = 5000, easing = LinearEasing),
//            repeatMode = RepeatMode.Reverse
//        ),
//        label = "alpha"
//    )
//    val colorsOne = listOf(
//        Color.Cyan,
//        MaterialTheme.colorScheme.primary,
//        MaterialTheme.colorScheme.tertiary,
//        MaterialTheme.colorScheme.onPrimaryContainer
//    )
//    val colorsTwo = listOf(
//        MaterialTheme.colorScheme.primary,
//        MaterialTheme.colorScheme.onPrimaryContainer,
//        Color.Black,
//        MaterialTheme.colorScheme.tertiary,
//    )
//    val animatedColorOne by infiniteTransition.animateColor(
//        initialValue = colorsOne[getRandom],
//        targetValue = colorsOne[getRandom],
//        animationSpec = infiniteRepeatable(
//            animation = tween(durationMillis = 3000, easing = LinearEasing),
//            repeatMode = RepeatMode.Reverse
//        ),
//        label = "animatedColorOne"
//    )
//
//    val animatedColorTwo by infiniteTransition.animateColor(
//        initialValue = colorsTwo[getRandom],
//        targetValue = colorsTwo[getRandom],
//        animationSpec = infiniteRepeatable(
//            animation = tween(durationMillis = 4000, easing = LinearEasing),
//            repeatMode = RepeatMode.Reverse
//        ),
//        label = "animatedColorTwo"
//    )
//
//    Canvas(modifier = modifier.fillMaxSize()) {
//        val gradient1 = Brush.linearGradient(
//            colors = listOf(animatedColorOne, animatedColorTwo),
//            start = Offset(0f, 0f),
//            end = Offset(size.width, size.height)
//        )
//        val gradient2 = Brush.linearGradient(
//            colors = listOf(animatedColorTwo, animatedColorOne),
//            start = Offset(size.width, 0f),
//            end = Offset(0f, size.height)
//        )
//        drawRect(brush = gradient1, alpha = animatedFloatAlpha)
//        drawRect(brush = gradient2, alpha = animatedFloatAlphaReverse)
//    }

//    Box(modifier = Modifier
//        .fillMaxSize()
//        .background(
//            color = Color.Cyan.copy(alpha = animatedFloatAlpha)
//        )
//    )
    ArcImageList(imageList = imageList)
    LaunchedEffect(key1 = backgroundState) {
        delay(4000)
        backgroundState = if(backgroundState == 1) 2 else 1
    }
}

private val getRandom: Int
    get() = Random.nextInt(0, 3)

@Composable
fun ArcImageList(imageList: List<Int>) {
    val itemTransformations = remember { mutableStateMapOf<Int, ItemTransformation>() }
    ArcLazyRow(
        modifier = Modifier.fillMaxSize(),
        radius = 180.dp,
        itemSpacing = 20.dp,
        itemTransformations = itemTransformations
    ) {
        items(imageList.size) { index ->
            val transformation = itemTransformations[index]
            Image(
                painter = painterResource(id = imageList[index]),
                contentDescription = "image $index",
                modifier = Modifier
                    .graphicsLayer {
                       translationX = itemTransformation[index].x
                       translationY = itemTransformation[index].y
                       rotationZ = itemTransformation[index].rotation
                    }
                    .padding(horizontal = 8.dp)
                    .height(120.dp)
                    .aspectRatio(.8f)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ArcLazyRow(
    modifier: Modifier = Modifier,
    radius: Dp,
    itemSpacing: Dp = 20.dp,
    itemTransformations: MutableMap<Int, ItemTransformation>,
    content: LazyListScope.() -> Unit
) {
    val lazyListState = rememberLazyListState()

    Layout(
        content = {
            LazyRow(
                modifier = modifier,
                state = lazyListState,
                content = content,
                horizontalArrangement = Arrangement.spacedBy(itemSpacing)
            )
        },
        modifier = modifier
    ) { measurables, constraints ->
        val rowConstraints = constraints.copy(minWidth = 0, minHeight = 0)
        val rowPlaceable = measurables.first().measure(rowConstraints)
        val arcRadius = radius.toPx()
        val itemCount = lazyListState.layoutInfo.totalItemsCount
        val visibleItemsInfo = lazyListState.layoutInfo.visibleItemsInfo
        val itemWidth = visibleItemsInfo.firstOrNull()?.size ?: 0
        val totalWidth = itemWidth * itemCount + itemSpacing.toPx() * (itemCount - 1)
        val arcLength = PI.toFloat() * arcRadius
        val scale = min(1f, totalWidth / arcLength)

        layout(constraints.maxWidth, constraints.maxHeight) {
            val centerX = constraints.maxWidth / 2
            val centerY = constraints.maxHeight

            rowPlaceable.placeRelative(0, centerY - rowPlaceable.height)

            visibleItemsInfo.forEach { itemInfo ->
                val index = itemInfo.index
                val angleRad = (PI.toFloat() / 2) + (index - (itemCount - 1) / 2f) * scale
                val x = centerX + (arcRadius * cos(angleRad)).toInt() - itemWidth / 2
                val y = centerY - (arcRadius * sin(angleRad)).toInt() + itemInfo.size / 2
                val rotationAngle = -(angleRad * 180f / PI.toFloat()) + 90f
                itemTransformations[index] = ItemTransformation(x.toFloat(), y.toFloat(), rotationAngle)
            }
        }
    }
}

data class ItemTransformation(
    val x: Float,
    val y: Float,
    val rotation: Float
)

val itemTransformation = listOf(
    ItemTransformation(20f, 100f, -20f),
    ItemTransformation(10f, 0f, 0f),
    ItemTransformation(-20f, 100f, 20f),
)

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
