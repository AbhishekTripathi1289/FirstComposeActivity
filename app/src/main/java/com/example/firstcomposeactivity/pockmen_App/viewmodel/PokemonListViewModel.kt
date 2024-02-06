package com.example.firstcomposeactivity.pockmen_App.viewmodel

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.example.firstcomposeactivity.pockmen_App.repo.PokemonRepo
import com.example.firstcomposeactivity.pockmen_App.util.PokemonConstants.PAGE_SIZE
import com.plcoding.composepagingyt.DefaultPaginator
import com.plcoding.composepagingyt.ScreenState
import com.plcoding.jetpackcomposepokedex.data.models.PokedexListEntry
import com.plcoding.jetpackcomposepokedex.data.remote.responses.PokemonList

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepo
) : ViewModel() {


    var state =  mutableStateOf(ScreenState<PokedexListEntry>())


    private var cachedPokemonList = listOf<PokedexListEntry>()
    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)


    private val paginator = DefaultPaginator<Int, PokedexListEntry>(
        initialKey = state.value.page,
        onLoadUpdated = {
            state.value = state.value.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            val result = repository.getPokemonList(PAGE_SIZE, nextPage * PAGE_SIZE)
            var exception: Throwable? = null
            var resultData = result?.getOrElse {
                exception = it
            }
            if(exception != null)
            {
                Result.failure(exception!!)
            }
            else
            {
                val pokedexEntries = (resultData as PokemonList).results.mapIndexed { index, entry ->
                    val number = if(entry.url.endsWith("/")) {
                        entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                    } else {
                        entry.url.takeLastWhile { it.isDigit() }
                    }
                    val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                    PokedexListEntry(entry.name.capitalize(Locale.ROOT), url, number.toInt())
                }
                Result.success(pokedexEntries)
            }
            // curPage++

            // loadError.value = ""
            // isLoading.value = false
            // pokemonList.value += pokedexEntries
        },
        getNextKey = {
            state.value.page + 1
        },
        onError = {
            state.value = state.value.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            state.value= state.value.copy(
                items = state.value.items + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    init {
        loadNextItems()
    }
    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    fun searchPokemonList(query: String) {
        val listToSearch = if(isSearchStarting) {
            state.value.items
        } else {
            cachedPokemonList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()) {
               // state.value.items = cachedPokemonList
                state.value= state.value.copy(
                    items = state.value.items + cachedPokemonList,
                )
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.pokemonName.contains(query.trim(), ignoreCase = true) ||
                        it.number.toString() == query.trim()
            }
            if(isSearchStarting) {
                cachedPokemonList = state.value.items
                isSearchStarting = false
            }
           // state.value.items = results
            state.value= state.value.copy(
                items = results,

                )
            isSearching.value = true
        }
    }


    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}