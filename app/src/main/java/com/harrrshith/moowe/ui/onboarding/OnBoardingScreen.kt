package com.harrrshith.moowe.ui.onboarding

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin


@SuppressLint("WrongConstant")
@Composable
fun OnBoardingRoute(
    navigateToHome: () -> Unit,
) {
    AnimatedBlobBackground(modifier = Modifier.fillMaxSize())
    OnBoardingScreen(
        modifier = Modifier
            .safeDrawingPadding()
            .safeContentPadding(),
        navigateToHome = { navigateToHome() }
    )
}

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
){
    var showAnimation by remember { mutableStateOf(false) }
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        AnimatedVisibility(visible = showAnimation, enter = expandVertically(expandFrom = Alignment.Bottom)) {
            Button(onClick = { navigateToHome() }) {
                Text(text = "Home", style = MaterialTheme.typography.titleLarge)
            }
        }
    }
    LaunchedEffect(key1 = true) {
        delay(1500)
        showAnimation = !showAnimation
    }
}
data class Blob(val color: Color, var x: Float, var y: Float)

@Composable
fun AnimatedBlobBackground(modifier: Modifier = Modifier) {
    val colors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.primaryContainer
    )

    val blobs = remember {
        List(4) { index ->
            Blob(colors[index], Math.random().toFloat(), Math.random().toFloat())
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "blobTransition")
    val animatedProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "blobAnimation"
    )

    val animatedProgressReverse by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(4800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "blobAnimation"
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        drawBlobBackground(blobs, animatedProgress % 1f)
        drawBlobBackground(blobs, animatedProgressReverse % 1f)
    }
}

private fun DrawScope.drawBlobBackground(blobs: List<Blob>, progress: Float) {
    val width = size.width / progress
    val height = size.height / progress
    // Update blob positions
    blobs.forEachIndexed { index, blob ->
        blob.x = (sin(progress * 2 * Math.PI + index * index * 0.3) * 0.4f + 0.5f).toFloat()
        blob.y = (cos(progress * 2 * Math.PI + index * 0.5) * 0.4f + 0.5f).toFloat()
    }
    // Create radial gradients for each blob
    val blobBrushes = blobs.map { blob ->
        Brush.radialGradient(
            colors = listOf(blob.color.copy(alpha = 0.7f), blob.color.copy(alpha = 0f)),
            center = Offset(blob.x * width, blob.y * height),
            radius = width * 0.5f
        )
    }

    // Draw blobs
    blobBrushes.forEach { brush ->
        drawRect(brush = brush, size = size)
    }

    // Apply blur effect
    drawRect(
        brush = Brush.verticalGradient(listOf(Color.Transparent, Color.White.copy(alpha = 0.2f))),
        blendMode = BlendMode.Lighten
    )
}

