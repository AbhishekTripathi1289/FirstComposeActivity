package com.example.firstcomposeactivity.compose.GenericCompose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun GenericImageCompose(
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    size: Dp = 20.dp
) {

    Image(
        painter = painterResource(id = icon),
        contentDescription = "",
        modifier.size(size)
    )

}