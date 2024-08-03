package com.harrrshith.moowe.ui.onboarding

import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

@Composable
fun OnBoardingRoute(
    navigateToHome: () -> Unit
) {
    AnimatedBlobBackground(modifier = Modifier.fillMaxSize())
    OnBoardingScreen(
        modifier = Modifier,
        navigateToHome = { navigateToHome() }
    )
}

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit
){
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Button(onClick = { navigateToHome() }) {
            Text(text = "Home", style = MaterialTheme.typography.titleLarge)
        }
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
            animation = tween(20000, easing = EaseInCubic),
            repeatMode = RepeatMode.Restart
        ),
        label = "blobAnimation"
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        drawBlobBackground(blobs, animatedProgress)
    }
}

private fun DrawScope.drawBlobBackground(blobs: List<Blob>, progress: Float) {
    val width = size.width
    val height = size.height

    // Update blob positions
    blobs.forEachIndexed { index, blob ->
        blob.x = (sin(progress * 2 * Math.PI + index * 0.5) * 0.4f + 0.5f).toFloat()
        blob.y = (cos(progress * 2 * Math.PI + index * 0.5) * 0.4f + 0.5f).toFloat()
    }
    // Create radial gradients for each blob
    val blobBrushes = blobs.map { blob ->
        Brush.radialGradient(
            colors = listOf(blob.color.copy(alpha = 0.7f), blob.color.copy(alpha = 0f)),
            center = Offset(blob.x * width, blob.y * height),
            radius = width * 0.6f
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

