package com.amaurypm.esprimojc.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.amaurypm.esprimojc.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.bryndanwrite)),
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        color = Color.White
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.bryndanwrite)),
        fontWeight = FontWeight.Normal,
        fontSize = 38.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        color = MyGray
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.bryndanwrite)),
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        color = Color.Black,
        textAlign = TextAlign.Center
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)