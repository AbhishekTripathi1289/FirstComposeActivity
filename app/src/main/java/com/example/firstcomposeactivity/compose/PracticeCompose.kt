package com.example.firstcomposeactivity.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.firstcomposeactivity.quateAppCheezyCode.ui.PracticeKotlin


@Composable
fun PracticeCompose() {
    var data = remember {
        mutableStateOf(1)
    }

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f).background(Color.Red),
            contentAlignment = Alignment.Center
        ) {

            Text(text = data.value.toString())
        }

        DummyCompose(data)
    }

}


@Composable
fun DummyCompose(data: MutableState<Int>) {


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxWidth(0.5f).background(Color.Yellow),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { data.value++ }) {
            Text(text = "Click Me (${data.value})")
        }
    }
}
