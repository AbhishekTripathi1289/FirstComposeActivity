package com.example.firstcomposeactivity.NotesApp.composes

import android.util.Log
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
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateNotesCompose(notes: Notes, isAlertDialogShow: MutableState<Boolean>, callback: (Notes) ->Unit)
{

    if(isAlertDialogShow.value)
    {
        var notesTitle = remember {
            mutableStateOf(notes.title)
        }
        var notesDesc = remember {
            mutableStateOf(notes.desc)
        }

        Log.d("#####", Gson().toJson(notes))
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { isAlertDialogShow.value = false },
            confirmButton = {
                OutlinedButton(onClick = {
                    notes.title = notesTitle.value
                    notes.desc= notesDesc.value
                    callback.invoke(notes)

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
