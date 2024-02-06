package com.example.firstcomposeactivity.pockmen_App.ui_screen.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.firstcomposeactivity.R
import com.example.firstcomposeactivity.pockmen_App.ui.theme.TypeDark
import com.example.firstcomposeactivity.pockmen_App.viewmodel.PokemonDetailViewModel
import com.plcoding.jetpackcomposepokedex.data.remote.responses.Pokemon

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokemonDetail(navController: NavController,
                  viewModel: PokemonDetailViewModel, pokemonName: String?) {

    LaunchedEffect(key1 = Unit){
        viewModel.fetchPokeDetail(pokemonName)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(TypeDark),
    ) {
        IconButton(
            onClick = {

            },modifier = Modifier
                .size(50.dp)
                .align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = ""
            )
        }

        when(val responseData = viewModel._pokeMonDetail.value){
            is DataState.Success ->
            {
                PokeMonDetailCardCompopsable(pokemonName, responseData)
            }
            is DataState.Loading->
            {
                LoadingComposable()
            }
            is DataState.Error->
            {
                ErrorComposable(responseData)
            }
             else ->{
            }
        }
    }
}


@Composable
fun ErrorComposable(errorData: DataState.Error) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = errorData.message.toString(),
            color = Color.Red,
        )
    }
}
@Composable
fun LoadingComposable() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.primary,
        )
    }
}
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokeMonDetailCardCompopsable(pokemonName: String?, responseData: DataState.Success<Pokemon>) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (box, image) = createRefs()
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.75f)
            .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .constrainAs(box) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        {
            Column(modifier = Modifier
                .fillMaxWidth()
                .offset(y = 80.dp)) {

                Text(text = "#18 $pokemonName", fontSize = 25.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                PokemonTypeCompose()

                ConstraintLayout(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)) {
                    var (column1, column2, devicder) = createRefs()

                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.constrainAs(column1){
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }) {
                        Icon(painter = painterResource(id = R.drawable.ic_weight), contentDescription = "")

                        Text(text = "39.5kg", color = Color.Black, modifier = Modifier.padding(top = 8.dp))
                    }

                    Spacer(modifier = Modifier
                        .height(80.dp)
                        .width(1.dp)
                        .background(Color.Gray)
                        .constrainAs(devicder) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        })

                    Column(horizontalAlignment = Alignment.CenterHorizontally , modifier = Modifier
                        .constrainAs(column2) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                        .padding(end = 5.dp))  {
                        Icon(painter = painterResource(id = R.drawable.ic_height), contentDescription = "")

                        Text(text = "1.5m", color = Color.Black, modifier = Modifier.padding(top = 8.dp))
                    }
                    createHorizontalChain(column1, devicder, column2, chainStyle = ChainStyle.Spread)
                }
            }
        }
        GlideImage(model = responseData.data.sprites.frontDefault ,
            contentDescription = "",
            modifier = Modifier
                .clip(CircleShape)
                .size(120.dp)
                .constrainAs(image) {
                    top.linkTo(box.top)
                    bottom.linkTo(box.top)
                    start.linkTo(box.start)
                    end.linkTo(box.end)
                },
        )
    }
}
@Composable
fun PokemonTypeCompose() {


    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp, start = 16.dp, end = 16.dp)) {

        Text(text = "Normal", fontSize = 16.sp, color = Color.White,
            modifier = Modifier
                .weight(1f)
                .height(35.dp)
                .padding(horizontal = 8.dp)
                .shadow(10.dp, CircleShape)
                .background(TypeDark)
                .wrapContentHeight(),
            textAlign = TextAlign.Center
            , fontWeight = FontWeight.Bold
        )

        Text(text = "Flying", fontSize = 16.sp, color = Color.White,
            modifier = Modifier
                .weight(1f)
                .height(35.dp)
                .padding(horizontal = 8.dp)
                .shadow(10.dp, CircleShape)
                .background(TypeDark)
                .wrapContentHeight(),
            textAlign = TextAlign.Center
            , fontWeight = FontWeight.Bold)
    }

}