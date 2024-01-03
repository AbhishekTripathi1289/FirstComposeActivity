package com.example.firstcomposeactivity.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun ConstraintLayoutExample()
{


    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (text1, text2, text3, button1) = createRefs()
        val guideline = createGuidelineFromTop(0.8f)

        Text(text = "hello text 1" ,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.constrainAs(text1){

        })
        Text(text = "hello text 1",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.constrainAs(text2){

        })
        Text(text = "hello text 1",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.constrainAs(text3) {

            })


        Button(onClick = {  }, modifier = Modifier.constrainAs(button1){
            top.linkTo(guideline)
            start.linkTo(parent.start)
            end.linkTo(parent.end)


        }) {
            Text(text = "Click Me")
        }

        createHorizontalChain(text1, text2, text3, chainStyle = ChainStyle.Spread)
    }
}