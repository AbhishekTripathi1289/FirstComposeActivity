package com.example.firstcomposeactivity.pockmen_App.ui_screen.composes

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.firstcomposeactivity.R
import com.example.firstcomposeactivity.models.Screens
import com.example.firstcomposeactivity.pockmen_App.ui.theme.LightBlue
import com.example.firstcomposeactivity.pockmen_App.ui.theme.RobotoCondensed
import com.example.firstcomposeactivity.pockmen_App.viewmodel.PokemonListViewModel
import com.plcoding.jetpackcomposepokedex.data.models.PokedexListEntry


@Composable
fun PokemonListCompose(navController: NavController,
                       viewModel: PokemonListViewModel) {

    Surface(
        color = LightBlue,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {


            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_international_pok_mon_logo),
                contentDescription = "Pokemon",
                modifier = Modifier
                    .fillMaxWidth()
            )

            SearchBarPokemon(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), "Search"){
                viewModel.searchPokemonList(it)
            }
            Spacer(modifier = Modifier.height(16.dp))
            PokemonList(navController = navController, viewModel)

        }
    }
}


@Composable
fun PokemonList(
    navController: NavController,
    viewModel: PokemonListViewModel
) {
    val pokemonList by remember { viewModel.state }
    val isSearching by remember { viewModel.isSearching }

    LazyVerticalGrid( columns = GridCells.Fixed(2) ,
        contentPadding = PaddingValues(5.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(start = 10.dp))
    {
        itemsIndexed(pokemonList.items) { index, item->
            if (index >= pokemonList.items.size - 1 && !pokemonList.endReached && !pokemonList.isLoading && !isSearching) {
                viewModel.loadNextItems()
            }
            PokedexEntry(
                entry = item,
                navController = navController,
                modifier = Modifier.aspectRatio(1f),
                viewModel
            )
        }
        item(span = {
            GridItemSpan(2)
        }) {
            if (pokemonList.isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if(pokemonList.error?.isNotEmpty() == true) {
            RetrySection(error = pokemonList.error!!) {
                viewModel.loadNextItems()
            }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokedexEntry(
    entry: PokedexListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(end = 10.dp)
            .shadow(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        dominantColor,
                        defaultDominantColor
                    )
                ),
            )
            .clickable {
                navController.navigate(
                    Screens.DetailScreen.withArgument(dominantColor.toArgb(), entry.pokemonName)
                )

            }
    ) {
        Column {
          GlideImage(model = entry.imageUrl , contentDescription = "",
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally),
          )
          Text(
                text = entry.pokemonName,
                fontFamily = RobotoCondensed,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(error, color = Color.Red, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun SearchBarPokemon(modifier: Modifier,
                     hint: String,
                     callback: (String)-> Unit,) {

    var isHintVisible = remember {
        mutableStateOf(true)
    }
    var text = remember {
        mutableStateOf("")
    }

    Box(modifier = modifier){
        BasicTextField(value = text.value,
            onValueChange = {
            text.value = it
                callback.invoke(it)
        },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(30.dp, CircleShape)
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintVisible.value = !it.isFocused && text.value.isEmpty()
                })

        if(isHintVisible.value)
        {
            Text(text = hint, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp), color = Color.Gray)
        }
    }



}