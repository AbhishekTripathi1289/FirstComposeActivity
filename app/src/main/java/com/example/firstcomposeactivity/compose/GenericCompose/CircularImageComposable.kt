package com.example.firstcomposeactivity.compose.GenericCompose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun CircularImageComposable(@DrawableRes icon: Int, modifier: Modifier)
{
    Image(painter = painterResource(id = icon), contentDescription = "", modifier = modifier)
}