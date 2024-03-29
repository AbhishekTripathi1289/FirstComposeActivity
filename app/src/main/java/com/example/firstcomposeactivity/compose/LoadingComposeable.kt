package com.example.firstcomposeactivity.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*Compose for show Loader*/
@Composable
fun LoadingCompose()
{
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {
        CircularProgressIndicator(
            modifier = Modifier.size(50.dp),
            color = Color.Blue,
            strokeWidth = 5.dp)
    }
}