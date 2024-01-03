package com.example.firstcomposeactivity.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeactivity.R


@Composable
fun LazyVerticalGridExample()
{
    var list = listOf<String>("abc", "dafs", "SADf", "dsaf", "dsaf", "adsf", "gjk")
    LazyVerticalGrid( columns = GridCells.Fixed(2) ,
        contentPadding = PaddingValues(5.dp),
        verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(top = 20.dp, end = 10.dp))
    {

        items(list)
        { data->
           Card(modifier = Modifier
               .paint(
                   painter = painterResource(id = R.drawable.profile_icon),
                   contentScale = ContentScale.Crop
               )
               .padding(start = 10.dp, bottom = 10.dp)
               .size(100.dp)
               .clip(RoundedCornerShape(5.dp)),
               border = BorderStroke(1.dp, Color.Black)
              )
           {
               Box(modifier = Modifier.fillMaxSize())
               {
                   Text(text = data, fontSize = 16.sp,
                       style = MaterialTheme.typography.bodyLarge,
                       color = Color.Red,
                       modifier = Modifier.align(Alignment.BottomCenter).background(Color.Yellow))
               }

           }
        }
    }
}