package com.example.firstcomposeactivity.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeactivity.pockmen_App.ui.theme.TypeDark

@Composable
fun TextViewWithCircleCornerBackgroundGenericCompose() {
    Text(text = "Normal", fontSize = 16.sp, color = Color.White,
        modifier = Modifier
            .width(120.dp)
            .height(35.dp)
            .shadow(10.dp, CircleShape)
            .background(TypeDark)
            .wrapContentHeight(),
        textAlign = TextAlign.Center
        , fontWeight = FontWeight.Bold)
}


