package com.example.firstcomposeactivity.pizzaApp.composes

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeactivity.R
import com.example.firstcomposeactivity.pizzaApp.module.Pizza
import com.example.firstcomposeactivity.pizzaApp.module.pizzaList
import com.example.firstcomposeactivity.ui.theme.LightGrayColor
import com.example.firstcomposeactivity.ui.theme.Purple200
import com.example.firstcomposeactivity.ui.theme.RedColor


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PizzAppHomeScreenCompose() {



    var scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState, topBar = {
        TopAppBar(
            title = {
            Text(text = "Jkm Restro", fontSize = 20.sp, color = Color.White , modifier = Modifier.padding(start = 10.dp))
        },
            navigationIcon = {
            Icon(painter = painterResource(id = R.drawable.menu),
                contentDescription = "",
                modifier = Modifier.padding(start = 5.dp)
                    , tint = Color.White)
        } ,
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = RedColor), actions = {
            Icon(painter = painterResource(id = R.drawable.search),
                contentDescription = "",
                modifier = Modifier.padding(end = 10.dp)
                    , tint = Color.White)
            })
    }) {

        Column(modifier = Modifier
            .fillMaxSize()
            .background(LightGrayColor)) {
            PizaHeaderChipSet(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 10.dp), arrayListOf("Starter", "Asian",
                "Placha & Roast & Grill", "Classic", "Indian", "Italian")){

            }
            PizaListCompose(modifier = Modifier, pizzaList)
        }
    }
}

@Composable
fun PizaListCompose(modifier: Modifier, listOfPiza: List<Pizza>) {

    LazyVerticalGrid(columns = GridCells.Fixed(2),
        modifier = Modifier.padding(top = 10.dp, end = 10.dp)){


        itemsIndexed(listOfPiza){index, item ->

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp).clip(
                    RoundedCornerShape(5.dp)
                ).background(Color.White)) {

                Image(painter = painterResource(item.image), contentDescription = "", modifier = Modifier.size(100.dp).padding(top = 8.dp))
                Text(text = item.price, color = RedColor,modifier = Modifier.padding(top = 8.dp))
                Text(text = item.name, color = Color.Black, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp))

                Text(text =item.description, fontSize = 12.sp, color = Color.DarkGray,
                    modifier = Modifier.padding(top = 8.dp) , textAlign = TextAlign.Center)
                Button(onClick = { /*TODO*/ }, shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Purple200),modifier = Modifier.padding(vertical = 8.dp)) {
                    Text(text = "Add")
                }
            }
        }
    }
}



@Composable
fun PizaHeaderChipSet(modifier: Modifier,
                      chipsList: ArrayList<String>, selectedChip: (Int) -> Unit)
{

    var selectedPos = remember {
        mutableStateOf(0)
    }


    Box(
        modifier = Modifier.fillMaxWidth().background(Color.White),
    ) {
        LazyRow(modifier = modifier.fillMaxWidth().background(Color.White)){
            itemsIndexed(chipsList) {index, item ->
                TextButton(onClick = { selectedPos.value = index }, shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = if (selectedPos.value == index) Purple200 else Color.Transparent),
                ) {

                    Text(text = item,
                        fontWeight = FontWeight.Bold,
                        color = if(selectedPos.value == index) Color.White else Color.Black)
                }

                /*   Text(text = item,
                      color = if(selectedPos.value == index) Color.White else Color.Black,
                      fontWeight = FontWeight.Bold,
                      modifier = Modifier
                          .padding(end = 10.dp)
                          .clip(CircleShape)
                          .background(if (selectedPos.value == index) Purple200 else Color.Transparent)
                          .padding(10.dp)
                          .clickable { selectedPos.value = index })*/
            }
        }
    }


}