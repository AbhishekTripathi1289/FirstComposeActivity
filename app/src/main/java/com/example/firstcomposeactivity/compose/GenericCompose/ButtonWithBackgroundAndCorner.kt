package com.example.firstcomposeactivity.compose.GenericCompose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun ButtonWithCornerComposable(modifier: Modifier, backGroundColor: Color,
                               foregroundColor: Color, text: String,
                               fontSize: TextUnit,
                               fontWeight: FontWeight,
                               shape: Shape,
                                textModiFier: Modifier = Modifier
                               , callback: ()-> Unit) {


    Button(
        onClick = { callback.invoke() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = backGroundColor,
            contentColor = foregroundColor
        ),
        shape = shape
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = fontWeight,
            modifier = textModiFier
        )
    }
}

@Composable
fun TextButtonWithCornerComposable(modifier: Modifier, backGroundColor: Color,
                               foregroundColor: Color, text: String,
                               fontSize: TextUnit,
                               fontWeight: FontWeight,
                               shape: Shape,
                               textModiFier: Modifier = Modifier
                               , callback: ()-> Unit) {


    TextButton(
        onClick = { callback.invoke() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = backGroundColor,
            contentColor = foregroundColor
        ),
        shape = shape
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = fontWeight,
            modifier = textModiFier
        )
    }
}