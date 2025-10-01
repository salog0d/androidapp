package com.example.proyecto.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.proyecto.R

val Gotham = FontFamily(
    Font(R.font.gotham_thin,       FontWeight.Thin),
    Font(R.font.gotham_light,      FontWeight.Light),
    Font(R.font.gotham_book,       FontWeight.Normal),
    Font(R.font.gotham_medium,     FontWeight.Medium),
    Font(R.font.gotham_bold,       FontWeight.Bold),
    Font(R.font.gotham_black,      FontWeight.Black),
    Font(R.font.gotham_ultra,      FontWeight.ExtraBold),
    Font(R.font.gotham_xlight,     FontWeight.ExtraLight),
    Font(R.font.gotham_lightitalic, FontWeight.Light, FontStyle.Italic),

)

val AppTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = Gotham,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Gotham,
        fontWeight = FontWeight.Medium,
        fontSize = 100.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Gotham,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Gotham,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
)
