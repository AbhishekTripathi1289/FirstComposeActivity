package com.example.firstcomposeactivity.starbucks.compose

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstcomposeactivity.NotesApp.viewmodel.NotesViewModel
import com.example.firstcomposeactivity.models.ChatScreens
import com.example.firstcomposeactivity.models.StarBuckScreen
import com.ramcosta.composedestinations.annotation.NavGraph

/*val notesViewModel: NotesViewModel by viewModels()
NotesListCompose(modifier = Modifier.padding(horizontal = 16.dp , vertical = 20.dp),
notesViewModel)*/
@Composable
fun StarBuckNavigation() {

    var navController = rememberNavController()

    NavHost(navController = navController, startDestination = StarBuckScreen.StartScreen.route){
        composable(StarBuckScreen.StartScreen.route){
        StartBuckStartScreen(navController = navController)
        }

        composable(StarBuckScreen.HomeScreen.route){
        StartBuckHomeScreenCompose(navController)
        }

        composable(StarBuckScreen.ProductDetailScreen.route){

        }
    }
}