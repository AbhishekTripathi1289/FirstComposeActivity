    package com.example.firstcomposeactivity.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.firstcomposeactivity.R


val MyCustomFont = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold),
)


// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily =MyCustomFont,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,

    ),
     //Other default text styles to override
    bodySmall = TextStyle(
        fontFamily =MyCustomFont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = MyCustomFont,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
)