package com.example.firstcomposeactivity.compose

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.firstcomposeactivity.models.BottomNavigationModel
import com.example.firstcomposeactivity.models.Screens
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ramcosta.composedestinations.annotation.Destination


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenWithBottomNavigation() {

    var navhostController = rememberNavController()

    Scaffold(bottomBar = {
        BottomNavigationCompose(modifierValue = Modifier,
            listOf(BottomNavigationModel(name = "Home", route = "home", icon = Icons.Default.Home),
                BottomNavigationModel(name = "Chat", route = "chat", icon = Icons.Default.Notifications),
                BottomNavigationModel(name = "Settings", route = "settings", icon = Icons.Default.Settings, badgeCount = 35)),
            navhostController){
            navhostController.navigate(it.route)
        }
    }) {
        PracticeCompose(navController = navhostController)
    }
}


@Composable
fun BottomNavigationCompose(
    modifierValue: Modifier,
    navItemList: List<BottomNavigationModel>,
    navHostController: NavHostController,
    callback: (BottomNavigationModel)->Unit
) {
    val backStackEntry = navHostController.currentBackStackEntryAsState()
    BottomNavigation(modifier = modifierValue, elevation = 5.dp, backgroundColor = Color.Gray) {

        navItemList.forEachIndexed{ index, bottomNavigationModel ->
            BottomNavigationItem(
                selected = bottomNavigationModel.route == backStackEntry.value?.destination?.route,
                onClick = { callback.invoke(bottomNavigationModel) },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.Red,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if(bottomNavigationModel.badgeCount > 0)
                        {
                            BadgedBox(badge = {
                                Text(text = bottomNavigationModel.badgeCount.toString())
                            } ) {
                                Icon(bottomNavigationModel.icon, contentDescription = "")
                            }
                        }
                        else{
                            Icon(bottomNavigationModel.icon, contentDescription = "")
                        }
                        if( bottomNavigationModel.route == backStackEntry.value?.destination?.route)
                        {
                            Text(text = bottomNavigationModel.name, fontSize = 10.sp)
                        }
                    }

                }
            )

        }

    }




}





@Destination(start = true)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun PracticeCompose(navController: NavHostController)
{
    NavHost(navController = navController, startDestination = "home") {

        composable(route = "home"){
            HomeScreen()
        }

        composable(route = "chat"){
            ChatScreen(navController)
        }
        composable(route = "settings"){
            SettingScreen(navController)
        }
    }
}

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {
        Text(text = "Home")
    }
}
@Composable
fun ChatScreen(navHostController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {
        Text(text = "Chat")
    }
    BackHandler {
        navHostController.navigate("home"){
            popUpTo("home"){
                inclusive = true
            }
        }
    }
}
@Composable
fun SettingScreen(navHostController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {
        Text(text = "Setting")
    }
    BackHandler {
        navHostController.navigate("home"){
            popUpTo("home"){
                inclusive = true
            }
        }
    }
}
