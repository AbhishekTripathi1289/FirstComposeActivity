package com.example.firstcomposeactivity.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun NavigationDrawerExampleCompose()
{
    var scafoldStateValue = rememberScaffoldState()
    var corutineScope = rememberCoroutineScope()

    androidx.compose.material.Scaffold(scaffoldState = scafoldStateValue, drawerContent = {
        Column {
            IconButton(onClick = { corutineScope.launch { scafoldStateValue.drawerState.close() }})
            {
                Icon(Icons.Default.Close, contentDescription = null, tint = Color.Blue)
            }
            Text(text = "I am Drawer Content")

        }
    }) { padding->
        Column(modifier = Modifier.padding(padding)) {
            IconButton(onClick = { corutineScope.launch { scafoldStateValue.drawerState.open() } })
            {
                Icon(
                    Icons.Default.List,
                    contentDescription = null,
                    tint = Color.Blue
                )
            }

            Image(imageVector = Icons.Default.List, contentDescription = "",
                colorFilter = ColorFilter.tint(Color.Blue), modifier = Modifier.padding(15.dp), alpha = 0.5f)
        }
    }
}