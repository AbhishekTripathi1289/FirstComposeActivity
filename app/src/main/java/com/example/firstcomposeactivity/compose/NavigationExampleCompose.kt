package com.example.firstcomposeactivity.compose


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.firstcomposeactivity.models.Screens
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ramcosta.composedestinations.annotation.Destination


@Destination(start = true)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun NavigationExampleCompose()
{
    var navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.route){

        composable(route = Screens.MainScreen.route){
            MainScreen(navController = navController)
        }
        composable(route = Screens.DetailScreen.route + "/{name}/{age}", arguments = listOf(
            navArgument("message"){
                type = NavType.StringType
                defaultValue = "I am Default"
                nullable = true
            },
            navArgument("age"){
                type = NavType.IntType
            }
        )){ entry->

            DetailScreen(message = entry.arguments?.getString("name"), person = entry.arguments?.getInt("age"))
        }
    }

}



@Composable
fun MainScreen(navController: NavController)
{

    Box( )
    {
        Button(onClick = {
            navController.navigate(Screens.DetailScreen.withArgument("Abhishek Tripathi", "37"))
        }) {
            Text(text = "Click Me")
        }
    }
}


@Composable
fun DetailScreen(message: String?, person: Int?)
{

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {
        Text(text = message!!)
        Text(text = person.toString(), modifier = Modifier.align(Alignment.BottomCenter))
    }
}

