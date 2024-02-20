package com.example.firstcomposeactivity.newsApp.ui.composes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.example.firstcomposeactivity.chatApp.Composes.GenericUrlImage

@Composable
fun NewsImageCompose(image: String?)
{
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        GenericUrlImage(imageUrl = image!!, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
            contentScale = ContentScale.FillBounds)
    }

}