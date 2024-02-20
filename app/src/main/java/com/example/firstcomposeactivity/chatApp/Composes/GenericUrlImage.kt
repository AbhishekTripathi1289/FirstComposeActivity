package com.example.firstcomposeactivity.chatApp.Composes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.firstcomposeactivity.R

/*Image Composable*/
@Composable
fun GenericUrlImage(modifier: Modifier = Modifier,imageUrl: String , contentScale: ContentScale) {

        AsyncImage(
            model = imageUrl,
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.placeholder),
            contentDescription = "Image",
            modifier = modifier,
            contentScale = contentScale
        )
}