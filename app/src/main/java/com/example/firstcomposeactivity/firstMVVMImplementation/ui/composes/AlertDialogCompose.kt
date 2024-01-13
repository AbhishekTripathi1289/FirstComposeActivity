package com.example.firstcomposeactivity.firstMVVMImplementation.ui.composes

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialog(isAlertDialogShow: MutableState<Boolean>)
{
    if(isAlertDialogShow.value)
    {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { isAlertDialogShow.value = false },
            confirmButton = {
                OutlinedButton(onClick = { isAlertDialogShow.value = false }) {
                    Text(text = "Click Me")
                } },
            title = {
                Text(text = "Hello world")
            },
            text = {
                Text(text = "Hello world")
            })
    }
}
