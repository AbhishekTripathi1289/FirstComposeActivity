package com.example.firstcomposeactivity.starbucks.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.firstcomposeactivity.R
import com.example.firstcomposeactivity.models.StarBuckScreen
import kotlinx.coroutines.delay

@Composable
fun StartBuckStartScreen(navController: NavController) {


    LaunchedEffect(key1 = Unit ){
        delay(3000)
        navController.navigate(StarBuckScreen.HomeScreen.route)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "")

    }
}