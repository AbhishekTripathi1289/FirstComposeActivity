package com.example.firstcomposeactivity.firstMVVMImplementation.ui.composes

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.firstcomposeactivity.firstMVVMImplementation.model.PostItem
import com.example.firstcomposeactivity.firstMVVMImplementation.viewmodel.PostViewModel
import com.google.gson.Gson


@Composable
fun PostListCompose(viewModel: PostViewModel)
{
    LaunchedEffect(key1 = Unit){
        viewModel.fetchPost()
    }
    when(val result = viewModel.postMutableState.value)
    {
        is DataState.Loading ->
        {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
            {
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp),
                    color = Color.Blue,
                    strokeWidth = 5.dp)
            }
        }
        is DataState.Success ->
        {
            ShowPostListCompose(result.data)
        }
        is DataState.Error ->
        {
           Text(text = result.message.toString())

        }
        is DataState.Empty ->
        {
            Log.d("####", "Empty")
        }
    }
}

@Composable
fun ShowPostListCompose(data: List<PostItem>) {
    
    LazyColumn( )
    {
        itemsIndexed(data){ index, post->
            var isAlertDialogShow = remember {
                mutableStateOf(false)
            }
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .clickable {
                    isAlertDialogShow.value = true
                },
                shape = RoundedCornerShape(6.dp),
                elevation = CardDefaults.cardElevation(5.dp), colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                )) {
                Log.d("####", isAlertDialogShow.value.toString())
                AlertDialog(isAlertDialogShow = isAlertDialogShow){

                }
                Text(text = post.body!!, fontSize = 25.sp,
                    style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                        .padding(10.dp))
            }
        }
    }
}
