package com.example.firstcomposeactivity.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun PracticeCompose()
{
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)) {
        Text(text = "Hello World", modifier = Modifier.background(Color.Black).weight(.2f))
        Text(text = "Hello World", modifier = Modifier.background(Color.Red).weight(.6f), textAlign = TextAlign.Center)
        Text(text = "Hello World", modifier = Modifier.background(Color.Yellow).weight(.2f))
    }
}