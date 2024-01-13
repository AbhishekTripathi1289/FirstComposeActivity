package com.example.firstcomposeactivity.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLineTextAndButtonWidgetComposable()
{
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(horizontal = 30.dp).fillMaxSize()) {
        OutlinedTextField(value = "", onValueChange ={
        } , label = {
            Text(text = "User Name")
        }, modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "User Name")
            },
            leadingIcon = {
                Icon(Icons.Default.Info, contentDescription = "")
            })


        OutlinedButton(onClick = { }, modifier = Modifier.fillMaxWidth().padding(top = 20.dp)) {
            Text(text = "Login")
        }
    }
}