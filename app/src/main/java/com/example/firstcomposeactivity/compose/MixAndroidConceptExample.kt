package com.example.firstcomposeactivity.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.flow


/*An Example which show how you can use a flow as a ComposeState*/
@Composable
fun flowAsState(): State<String> {
    return flow<String> {
        emit("dsaf")
    }.collectAsState(initial = "")
}
