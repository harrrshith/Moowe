package com.harrrshith.moowe.ui.onboarding

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.harrrshith.moowe.ui.theme.MooweTheme
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
    var startAnimation by remember { mutableStateOf(false) }
    var startAlpha by remember { mutableStateOf(false) }
    var startButtonAlpha by remember { mutableStateOf(false) }
    val animatedOffsetY by  animateFloatAsState(
        targetValue = if (startAnimation) 0f else 1500f,
        label = "offsetY"
    )
    val animatedAlpha by animateFloatAsState(
        targetValue = if (startAlpha) 1f else .5f,
        label = "alpha"
    )
    val animatedButtonAlpha by animateFloatAsState(
        targetValue = if (startButtonAlpha) 1f else 0f,
        label = "alpha"
    )
    val animatedButtonWidth by animateFloatAsState(
        targetValue = if (startButtonAlpha) 180f else 0f,
        label = "width"
    )
    val animatedButtonHeight by animateFloatAsState(
        targetValue = if (startButtonAlpha) 50f else 0f,
        label = "width"
    )
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(
            text = "Moowe",
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .offset { IntOffset(x = 0, y = animatedOffsetY.toInt()) }
                .alpha(animatedAlpha)
        )
        Button(
            onClick = { navigateToHome() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp)
                .alpha(animatedButtonAlpha)
                .height(animatedButtonHeight.dp)
                .width(animatedButtonWidth.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Text(
                text = "Get Started",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }

    LaunchedEffect(Unit) {
        delay(1000)
        startAnimation = !startAnimation
        delay(200)
        startAlpha = !startAlpha
        delay(200)
        startButtonAlpha = !startButtonAlpha
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
            animation = tween(10000, easing = LinearEasing),
        ),
        label = "blobAnimation"
    )

    val animatedProgressReverse by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
            initialStartOffset = StartOffset(100, StartOffsetType.Delay)
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

@Preview
@Composable
fun PreviewOnBoardingScreen() {
    MooweTheme {
        Surface {
            OnBoardingScreen(
                modifier = Modifier.fillMaxSize(),
                navigateToHome = {}
            )
        }
    }
}

