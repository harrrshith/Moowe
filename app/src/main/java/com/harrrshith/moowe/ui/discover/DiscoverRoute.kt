package com.harrrshith.moowe.ui.discover

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import com.harrrshith.moowe.R
import kotlin.random.Random

@Composable
fun DiscoverRoute(

){
    val imageList = listOf(
        R.drawable.image_one,
        R.drawable.image_two,
        R.drawable.image_three,
        R.drawable.image_four,
        R.drawable.image_five,
    )
    val randomNumber = Random.nextInt(0, imageList.size - 1)
    val imageBitmap = ImageBitmap.imageResource(id = imageList[randomNumber])
    val colorList = remember {
        extractColors(imageBitmap)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(colors = colorList)),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = imageList[randomNumber]),
            contentDescription = "An image",
            modifier = Modifier
                .width(400.dp)
                .aspectRatio(2f)
        )
    }
}

private fun extractColors(imageBitmap: ImageBitmap): List<Color> {
    val palette = Palette.from(imageBitmap.asAndroidBitmap()).generate()
    var vibrantColor: Color
    var lightVibrantColor: Color
    var dominantColor: Color
    palette.also {
        vibrantColor = Color(it.getVibrantColor(0x0000))
        lightVibrantColor = Color(it.getLightVibrantColor(0xFFFFFF))
        dominantColor = Color(it.getDominantColor(0xFFFFFF))
    }
    return listOf(vibrantColor, lightVibrantColor, dominantColor)
}