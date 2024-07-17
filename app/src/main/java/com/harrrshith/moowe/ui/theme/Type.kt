package com.harrrshith.moowe.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.harrrshith.moowe.R

// Set of Material typography styles to start with
val roboto = FontFamily(
    Font(R.font.raleway_black, FontWeight.Black),
    Font(R.font.raleway_black, FontWeight.Bold),
    Font(R.font.raleway_light, FontWeight.Light),
    Font(R.font.raleway_medium, FontWeight.Medium),
    Font(R.font.raleway_regular, FontWeight.Normal),
    Font(R.font.raleway_thin, FontWeight.Thin),
)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.5.sp
    ),
    displayLarge = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Black,
        fontSize = 24.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.5.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Thin,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)