package com.example.firstcomposeactivity.newsApp.ui.composes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.firstcomposeactivity.R
import com.example.firstcomposeactivity.compose.LoadingCompose
import com.example.firstcomposeactivity.newsApp.viewmodel.NewsViewModel
import com.example.firstcomposeactivity.ui.theme.DarkOrange


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsMainScreenCompose(viewModel: NewsViewModel) {

    Scaffold(topBar = {
        TopAppBar(title = {
                Text(
                    text = stringResource(R.string.news_app),
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = DarkOrange))
    }) { paddingvalues->

        LaunchedEffect(key1 = Unit){
            viewModel.fetchPost("India")
        }
        /*Handle all cases of api*/
        when(val result = viewModel._newLiveData.value)
        {
            is DataState.Loading ->
            {
                LoadingCompose()
            }
            is DataState.Success ->
            {
                NewsAppNavigationCompose(modifier = Modifier.padding(paddingvalues), result.data)
            }
            is DataState.Error ->
            {
                /*Show message for Error Case*/
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = result.message.toString())
                }
            }
            is DataState.Empty ->
            {
            }
        }
    }
}