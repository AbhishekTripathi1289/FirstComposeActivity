package com.example.firstcomposeactivity.pockmen_App.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.firstcomposeactivity.pockmen_App.repo.PokemonRepo
import com.plcoding.jetpackcomposepokedex.data.remote.responses.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(val repo: PokemonRepo): ViewModel() {


    private val pokeMonDetail= mutableStateOf<DataState<Pokemon>>(DataState.Empty)
    val _pokeMonDetail: State<DataState<Pokemon>> = pokeMonDetail

    fun fetchPokeDetail(pokemon: String?)
    {
        viewModelScope.launch {

            repo.getPokemonDetail(pokemon).onEach {
                pokeMonDetail.value = it
            }.launchIn(viewModelScope)
        }
    }

}