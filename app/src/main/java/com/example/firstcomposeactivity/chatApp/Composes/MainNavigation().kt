package com.example.firstcomposeactivity.chatApp.Composes

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstcomposeactivity.NotesApp.viewmodel.NotesViewModel
import com.example.firstcomposeactivity.models.ChatScreens
import com.ramcosta.composedestinations.annotation.NavGraph

/*val notesViewModel: NotesViewModel by viewModels()
NotesListCompose(modifier = Modifier.padding(horizontal = 16.dp , vertical = 20.dp),
notesViewModel)*/
@Composable
fun MainNavigation() {

    var navController = rememberNavController()

    NavHost(navController = navController, startDestination = ChatScreens.HomeScreen.route){
        composable(ChatScreens.HomeScreen.route){
            HomeScreenCompose(navController = navController)
        }

        composable(ChatScreens.ChatListSceen.route){
        ChatListScreen(navController = navController)
        }

        composable(ChatScreens.ChatDetailScreen.route){
            ChatDetailCompose(navController = navController)
        }
    }
}