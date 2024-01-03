package com.example.firstcomposeactivity.compose

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.firstcomposeactivity.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun launchEffectsSideEffect()
{
    var data = remember {
        mutableStateOf(emptyList<String>())
    }

    var dataSecond = remember {
        mutableStateOf("abc")
    }

    LaunchedEffect(key1 = dataSecond.value)
    {
        data.value = fetchDataFromApi()
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter)
    {
        Text(text = "Helloo world")

        LazyColumn{
            itemsIndexed(data.value)
            {index, item->

                Text(text = item)
            }
        }
    }
}

suspend fun  fetchDataFromApi(): List<String> {
    delay(5000)
    return arrayListOf("abc", "dsaf" , "dsaf")
}

@Composable
fun RememberCoroutineScopeSideEffect()
{
    var context = rememberCoroutineScope()

    var data = remember {
        mutableStateOf(emptyList<String>())
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter)
    {
        Button(onClick = { context.launch { data.value = fetchDataFromApi() } }) {
            Text(text = "Click Me")
        }
        LazyColumn{
            itemsIndexed(data.value)
            {index, item->

                Text(text = item)
            }
        }
    }
}


@Composable
fun RememberStateUpdatedState(onTimeout: () -> Unit)
{
    // This will always refer to the latest onTimeout function that
    // LandingScreen was recomposed with
    val currentOnTimeout by rememberUpdatedState(onTimeout)

    // Create an effect that matches the lifecycle of LandingScreen.
    // If LandingScreen recomposes, the delay shouldn't start again.
    LaunchedEffect(true) {
        delay(5000)
        currentOnTimeout()
    }

    /* Landing screen content */
}


@Composable
fun DisposableEffectSideEffectExample()
{
    val context = LocalContext.current
    DisposableEffect(Unit){
        val mediaPlayer = MediaPlayer.create(context, com.google.android.material.R.layout.material_clockface_textview)
        mediaPlayer.start()

        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }
}




@Composable
fun ProduceStateSideEffectExample()
{
    SideEffect {
        Log.d("####", "compose called")
    }
    var state = produceState(initialValue = 0, producer = {
        Log.d("####", "producered called")

        while (value <10)
        {
            delay(1000)
            value++
        }
    })
    Text(text = "${state.value}", color = Color.White
        , modifier = Modifier.size(30.dp).drawBehind { drawCircle(color = Color.Red,
            radius = this.size.maxDimension) }.clip(RoundedCornerShape(5.dp)).border(width = 1.dp, color = Color.Black ))

}




