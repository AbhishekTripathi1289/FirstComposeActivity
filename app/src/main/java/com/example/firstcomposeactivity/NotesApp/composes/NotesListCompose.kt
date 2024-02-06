package com.example.firstcomposeactivity.NotesApp.composes

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.firstcomposeactivity.NotesApp.room.Notes
import com.example.firstcomposeactivity.NotesApp.viewmodel.NotesViewModel
import com.example.firstcomposeactivity.firstMVVMImplementation.ui.composes.AlertDialog
import com.example.firstcomposeactivity.ui.theme.Purple200
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotesListCompose(modifier: Modifier, notesViewModel: NotesViewModel) {

    var scafoldState = rememberScaffoldState()

    LaunchedEffect(key1 = Unit){
        notesViewModel.getAllNotes()
    }


    var isAddAlertDialogShow = remember {
        mutableStateOf(false)
    }
    var isUpdateAlertDialogShow = remember {
        mutableStateOf(false)
    }

    var selectedNotes  = remember {
        mutableStateOf<Notes>(Notes(title = "qwe", desc =  "dsfa"))
    }
    var coroutineScope = rememberCoroutineScope()
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            isAddAlertDialogShow.value = true
                                       },
            backgroundColor =  Purple200) {
            Icon(Icons.Default.Add, contentDescription = "")
        }
    }, scaffoldState = scafoldState) {

        AlertDialog(isAlertDialogShow =isAddAlertDialogShow){
            notesViewModel.insertStudent(it)
        }
        UpdateNotesCompose(notes = selectedNotes.value ,
            isAlertDialogShow = isUpdateAlertDialogShow){
            coroutineScope.launch{
                async {
                    notesViewModel.insertStudent(it)
                }.await()
                notesViewModel.getAllNotes()
            }
        }
        Column(modifier = modifier.fillMaxSize()) {
            SearchBarCompose(modifier = Modifier){
                notesViewModel.searchNotes(it)
            }
            when(val result = notesViewModel._notesState.value)
            {
                is DataState.Loading ->{
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.material3.CircularProgressIndicator(
                            modifier = Modifier.size(50.dp),
                            color = Color.Blue,
                            strokeWidth = 5.dp
                        )
                    }
                }

                is DataState.Success ->{
                    NotesList(modifier= Modifier.padding(top = 10.dp), result.data){notes, isUpdate->
                        Log.d("#####", "Update Calling")

                        if(isUpdate)
                        {
                            selectedNotes.value = notes
                            isUpdateAlertDialogShow.value = true
                        }
                        else{
                            notesViewModel.deleteNotes(notes = notes)
                        }
                    }
                }
                is DataState.Error ->{
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = result.message.toString(), fontSize = 24.sp, color = Color.Red)
                    }
                }
                is DataState.Empty ->{
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "No Notes Found")
                    }
                }
                else -> {

                }
            }

        }
    }
}


@Composable
fun NotesList(modifier: Modifier, notesList: ArrayList<Notes>, callback: (Notes, Boolean) -> Unit) {

    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = modifier){
        itemsIndexed(notesList){index, item ->
        NotesItem(note = item){notes, isupdate->
            callback.invoke(item, isupdate)
        }
        }
    }

}


@Composable
fun NotesItem(note: Notes, callback: (Notes, Boolean) -> Unit) {


    Column(modifier = Modifier
        .aspectRatio(1.0f)
        .padding(start = 8.dp, bottom = 8.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(Color.LightGray)
        .clickable { callback.invoke(note, true) }, horizontalAlignment = Alignment.CenterHorizontally) {

        Row(modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(text = note.title, fontSize = 20.sp,
                fontWeight = FontWeight.Bold, color = Color.Black)
            Icon(Icons.Default.Delete, contentDescription = "", tint = Purple200,
                modifier = Modifier.clickable { callback.invoke(note, false) })
        }
        Text(modifier = Modifier.padding(top = 8.dp, start = 5.dp, end = 5.dp), text =note.desc,
            fontSize = 16.sp,
            maxLines = 2,
            color = Color.Gray,
            overflow = TextOverflow.Ellipsis)
    }
}
@Composable
fun SearchBarCompose(modifier: Modifier, callback: (String) -> Unit) {
    var searchTextState = remember {
        mutableStateOf("")
    }

    TextField(value = searchTextState.value, onValueChange = {
        searchTextState.value = it
        callback.invoke(it)
    },
        placeholder = {
            Text(text = "Search Notes...",  color = Color.Gray)
        }, leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "", tint = Purple200)
        }, trailingIcon = {
            if(searchTextState.value.isNotEmpty()) {
                Icon(Icons.Default.Close, contentDescription = "", tint = Purple200,
                    modifier = Modifier.clickable {  searchTextState.value = ""
                                                    callback.invoke("")})
            }
        }, modifier = modifier
            .fillMaxWidth()
            , shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.LightGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            disabledTextColor = Color.Transparent))
}