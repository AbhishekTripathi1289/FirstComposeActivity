package com.example.firstcomposeactivity.newsApp.ui.composes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.firstcomposeactivity.newsApp.model.NewsAppScreen
import com.example.firstcomposeactivity.newsApp.model.NewsModel

@Composable
fun NewsAppNavigationCompose(modifier: Modifier, newsListL : List<NewsModel>) {

    var navController = rememberNavController()

    NavHost(navController = navController, startDestination = NewsAppScreen.HomeScreen.route){
        composable(route = NewsAppScreen.HomeScreen.route){
            NewsRootCompose( modifier = modifier, newsListL, navController)
        }

        composable(route = NewsAppScreen.ImageScreen.route+"/{image}", arguments = listOf(
            navArgument("image"){
                type = NavType.StringType
                defaultValue = "https://c.biztoc.com/268/og.png"
            }
        )){ entry->
        NewsImageCompose(entry?.arguments?.getString("image"))
        }

    }
}