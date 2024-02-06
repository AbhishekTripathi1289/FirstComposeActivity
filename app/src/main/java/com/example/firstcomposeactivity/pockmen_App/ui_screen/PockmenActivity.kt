package com.example.firstcomposeactivity.pockmen_App.ui_screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.firstcomposeactivity.models.Screens
import com.example.firstcomposeactivity.pockmen_App.ui.theme.FirstComposeActivityTheme
import com.example.firstcomposeactivity.pockmen_App.ui_screen.composes.PokemonDetail
import com.example.firstcomposeactivity.pockmen_App.ui_screen.composes.PokemonListCompose
import com.example.firstcomposeactivity.pockmen_App.viewmodel.PokemonDetailViewModel
import com.example.firstcomposeactivity.pockmen_App.viewmodel.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class PockmenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstComposeActivityTheme {

                val viewModel: PokemonListViewModel by viewModels()
                val viewModelDetail: PokemonDetailViewModel by viewModels()

                var navController = rememberNavController()

                NavHost(navController = navController, startDestination = Screens.MainScreen.route){

                    composable(route = Screens.MainScreen.route)
                    {
                       PokemonListCompose(navController, viewModel)
                    }

                    composable(route = Screens.DetailScreen.route + "/{dominateColor}/{pokemonName}", arguments = listOf(
                        navArgument("dominateColor"){
                        type = NavType.IntType
                    },
                        navArgument("pokemonName"){
                            type = NavType.StringType
                        }
                    ))
                    {
                        var domimnatenColor = remember {
                            var color =it.arguments?.getInt("dominateColor")
                            color?.let { Color(it)  }?:Color.White
                        }
                        val pokemonName = remember {
                            it.arguments?.getString("pokemonName")
                        }
                         PokemonDetail(navController, viewModelDetail, pokemonName?.toLowerCase(
                             Locale.ROOT) ?: "")
                    }


                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    FirstComposeActivityTheme {
        Greeting2("Android")
    }
}