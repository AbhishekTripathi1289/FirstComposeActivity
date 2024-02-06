package com.example.firstcomposeactivity.NotesApp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.firstcomposeactivity.NotesApp.repo.NotesRepo
import com.example.firstcomposeactivity.NotesApp.room.Notes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NotesViewModel @Inject constructor(private var repo:NotesRepo) :ViewModel()
{

    private val notesState = mutableStateOf<DataState<ArrayList<Notes>>>(DataState.Empty)
    val _notesState: State<DataState<ArrayList<Notes>>> = notesState
    private var cachedPokemonList = arrayListOf<Notes>()
    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)


    fun getAllNotes(){
        viewModelScope.launch{
            repo.getAllNotes().onEach {
                notesState.value = it
                if(it is DataState.Success) {
                cachedPokemonList =   it.data
                }

            }.launchIn(viewModelScope)
        }
    }

    fun searchNotes(query: String) {
        val listToSearch = if(isSearchStarting) {
            (notesState.value as DataState.Success).data
        } else {
            cachedPokemonList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()) {
                // state.value.items = cachedPokemonList
                notesState.value = DataState.Success(cachedPokemonList)
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.title.contains(query.trim(), ignoreCase = true)
            }
            if(isSearchStarting) {
                cachedPokemonList = (notesState.value as DataState.Success).data
                isSearchStarting = false
            }

            notesState.value = if(results.isNullOrEmpty()){
                DataState.Empty
            }
            else
            {
                DataState.Success(results as ArrayList<Notes>)
            }
            isSearching.value = true
        }
    }
    fun insertStudent(notes: Notes)
    {
        viewModelScope.launch {
            repo.inserNotes(notes)
        }
    }

    fun deleteNotes(notes: Notes)
    {
        viewModelScope.launch {
            repo.deleteNotes(notes)
        }
    }
}