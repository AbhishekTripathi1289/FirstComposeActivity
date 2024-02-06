package com.example.firstcomposeactivity.firstMVVMImplementation.ui.composes

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.firstcomposeactivity.NotesApp.room.Notes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialog(isAlertDialogShow: MutableState<Boolean>, callback: (Notes) ->Unit)
{

    if(isAlertDialogShow.value)
    {
        var notesTitle = remember {
            mutableStateOf("")
        }
        var notesDesc = remember {
            mutableStateOf("")
        }
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { isAlertDialogShow.value = false },
            confirmButton = {
                OutlinedButton(onClick = {
                    callback.invoke(Notes(title = notesTitle.value, desc = notesDesc.value))
                    notesTitle.value = ""
                    notesDesc.value = ""
                    isAlertDialogShow.value = false
                }) {
                    Text(text = "Save Notes")
                } },
            title = {
                Text(text = "Add Notes")
            },
            text = {
              Column {
                  TextField(value = notesTitle.value, onValueChange = {
                    notesTitle.value = it
                  })
                  TextField(value = notesDesc.value, onValueChange = {
                    notesDesc.value = it
                  })
              }
            })
    }
}
