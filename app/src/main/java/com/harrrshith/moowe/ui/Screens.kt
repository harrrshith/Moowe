package com.harrrshith.moowe.ui

import android.graphics.RectF
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harrrshith.moowe.ui.theme.MooweTheme
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
//    val scrollState = rememberScrollState()
//    val listItems = listOf("A", "B")
//    Box(modifier = Modifier
//        .horizontalScroll(scrollState)
//        .fillMaxWidth()
//        .height(400.dp)
//        .drawBehind {
//            val radius = size.height / 2f
//            val itemWidth = size.width / listItems.size
//
//            listItems.forEachIndexed { index, _ -> // Assuming we don't need the item name for arcs
//                val startAngle = (index * 360f / listItems.size).toFloat()
//                val sweepAngle = 360f / listItems.size // Equal width for each item
//                val endAngle = startAngle + sweepAngle
//
//                // Calculate the position for the center of the arc
//                val centerX = size.width / 2f
//                val centerY = size.height / 2f
//
//                // Draw the arc
//                drawContext.canvas.nativeCanvas.drawArc(
//                    RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius),
//                    startAngle,
//                    sweepAngle,
//                    false,
//                    android.graphics.Paint().apply {
//                        color = android.graphics.Color.BLACK
//                    }
//                )
//            }
//        }
//    )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = { navigateToHome() }) {
            Text(text = "Click Here")
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