package com.example.firstcomposeactivity.compose.GenericCompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CircleBackgroundWithIconInsideInCenter(boxSize: Int,
                                    iconSize: Int,
                                    boxBackgroud: Color,
                                    iconTintColor: Color = Color.Unspecified,
                                           imageResource : Int = 0,
                                           imageVector: ImageVector? = null, modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .size(boxSize.dp)
            .background(boxBackgroud, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        if(imageResource !=  0)
        {
            Icon(painter = painterResource(id = imageResource),
                contentDescription = "", modifier = Modifier.size(iconSize.dp), tint = iconTintColor)
        }
        else if (imageVector != null){
            Icon(imageVector = imageVector, contentDescription = ""
                ,modifier = Modifier.size(iconSize.dp), tint = iconTintColor)

        }
    }
}