package com.example.firstcomposeactivity.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.firstcomposeactivity.models.BottomNavigationModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarBottomNavigationNavigationDrawer() {

    var scafoldStateValue = rememberScaffoldState()
    var corutineScope = rememberCoroutineScope()
    var navhostController = rememberNavController()


    androidx.compose.material.Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "AppBar", color = Color.White, modifier = Modifier.padding(start = 10.dp)) },
            navigationIcon = {
                Icon(Icons.Default.List, contentDescription = "", tint = Color.White,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable {
                            corutineScope.launch { scafoldStateValue.drawerState.open() }
                        })
            }, colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Blue),
        )

    }, drawerContent = {
        NavigationDrawerContent(modifier = Modifier,  listOf(
            BottomNavigationModel(name = "Home", route = "home", icon = Icons.Default.Home),
            BottomNavigationModel(name = "Chat", route = "chat", icon = Icons.Default.Notifications),
            BottomNavigationModel(name = "Settings", route = "settings", icon = Icons.Default.Settings, badgeCount = 35)
        )){
            navhostController.navigate(it.route)
            corutineScope.launch {
                scafoldStateValue.drawerState.close()
            }
        }
    }, scaffoldState = scafoldStateValue, bottomBar = {
        BottomNavigationCompose(modifierValue = Modifier,
            listOf(
                BottomNavigationModel(name = "Home", route = "home", icon = Icons.Default.Home),
                BottomNavigationModel(name = "Chat", route = "chat", icon = Icons.Default.Notifications),
                BottomNavigationModel(name = "Settings", route = "settings", icon = Icons.Default.Settings, badgeCount = 35)
            ),
            navhostController){
            navhostController.navigate(it.route)
        }
    }) { padding->
        PracticeCompose(navController = navhostController)
    }

}


@Composable
fun NavigationDrawerContent(modifier: Modifier, list: List<BottomNavigationModel>, callback: (BottomNavigationModel)-> Unit) {

    var selectedItem = remember {
        mutableStateOf(0)
    }

    LazyColumn(modifier = modifier.fillMaxSize()){

        itemsIndexed(list){ index, listItem->

            Row(modifier = Modifier.fillMaxWidth()
                .background(if(selectedItem.value == index) Color.Gray else  Color.White).clickable
                {
                    selectedItem.value = index
                    callback.invoke(listItem)
                },
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = listItem.name)
                Icon(listItem.icon, contentDescription = "")
            }
        }
    }

}

