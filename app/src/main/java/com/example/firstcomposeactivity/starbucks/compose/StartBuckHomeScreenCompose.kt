package com.example.firstcomposeactivity.starbucks.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.firstcomposeactivity.R
import com.example.firstcomposeactivity.compose.GenericCompose.ButtonWithCornerComposable
import com.example.firstcomposeactivity.compose.GenericCompose.CircleBackgroundWithIconInsideInCenter
import com.example.firstcomposeactivity.compose.GenericCompose.CircularImageComposable
import com.example.firstcomposeactivity.compose.GenericCompose.GenericIconComponent
import com.example.firstcomposeactivity.compose.GenericCompose.GenericImageCompose
import com.example.firstcomposeactivity.compose.GenericCompose.TextButtonWithCornerComposable
import com.example.firstcomposeactivity.ui.theme.Background
import com.example.firstcomposeactivity.ui.theme.DarkGray_1
import com.example.firstcomposeactivity.ui.theme.DarkGreen
import com.example.firstcomposeactivity.ui.theme.Gray_1
import com.example.firstcomposeactivity.ui.theme.LightGray
import com.example.firstcomposeactivity.ui.theme.LightGrayColor
import com.example.firstcomposeactivity.ui.theme.LightRed_1
import com.example.firstcomposeactivity.ui.theme.TextColor
import com.nameisjayant.chatappyt.starbucks.navigation.data.menuList

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StartBuckHomeScreenCompose(navController: NavController) {

    var scrollstate  = rememberScrollState()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 20.dp)
        .background(Background)) {

        Header()
        Column(Modifier
            .fillMaxSize().verticalScroll(scrollstate)) {

        Text(text = "Our way of loving\nyou back", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color.Black,
            modifier = Modifier.padding(vertical = 25.dp), textAlign = TextAlign.Start)
        SearchBarCompose()

        FilterCategoryCompose()

        CockletListCompose()
        }
    }
}


@Composable
fun CockletListCompose() {


    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp)) {

        var (popularText, seeAllText, listCrousal) = createRefs()



        Text(
            text = "Popular",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.constrainAs(popularText) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = "See All",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Green,
            modifier = Modifier.constrainAs(seeAllText) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }
        )


        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .constrainAs(listCrousal) {
                start.linkTo(parent.start)
                top.linkTo(popularText.bottom)
                end.linkTo(parent.end)
            },horizontalArrangement = Arrangement.spacedBy(16.dp)) {

            items(5){

                CockolateItemCompose()
            }
        }
    }
}

@Composable
fun CockolateItemCompose() {


    ConstraintLayout(modifier = Modifier
        .width(220.dp)
        .background(LightGrayColor, RoundedCornerShape(14.dp))){


        var (imagebox, title, price, heartIcon) = createRefs()


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    LightRed_1, RoundedCornerShape(
                        14.dp
                    )
                )
                .constrainAs(imagebox) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentAlignment = Alignment.Center
        ) {

            GenericImageCompose(icon = R.drawable.chocklete, size = 180.dp, modifier = Modifier)
        }


        Text(
            text = "Choclate Cappuccino",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(imagebox.bottom)
                    start.linkTo(parent.start)
                }
                .padding(start = 16.dp, bottom = 5.dp, top = 20.dp, end = 16.dp), maxLines = 1, overflow = TextOverflow.Ellipsis
        )

        Text(
            text = "$20.00",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Green,
            modifier = Modifier
                .constrainAs(price) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                }
                .padding(start = 16.dp, bottom = 16.dp)
        )
        
        GenericIconComponent(icon = R.drawable.love, size = 20.dp,
            modifier = Modifier
                .constrainAs(heartIcon) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(bottom = 20.dp, end = 16.dp))
    }
}
@Composable
fun FilterCategoryCompose()
{
    var selectedPos = remember {
        mutableStateOf(0)
    }
    LazyRow(modifier = Modifier.padding(top = 16.dp)){

        itemsIndexed(menuList){index, item ->

            TextButtonWithCornerComposable(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                backGroundColor = if(selectedPos.value == index) DarkGreen else Gray_1,
                foregroundColor =if (selectedPos.value == index) Color.White else TextColor ,
                text = item.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.W400,
                shape = RoundedCornerShape(22.dp),
                textModiFier = Modifier.padding(vertical = 5.dp)
            ) {
                selectedPos.value = index
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarCompose() {
    Box(
        modifier = Modifier.fillMaxWidth(),

    ) {

        var textfield = remember {
            mutableStateOf("")
        }
        TextField(value = textfield.value, onValueChange ={
            textfield.value =it
        },
            placeholder = {
                Text(text = "Search", color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.W300, textAlign = TextAlign.Center)
            },
            leadingIcon = {
                GenericIconComponent(icon = R.drawable.ic_search, tint = Color.Black)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,),
            shape = CircleShape, textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal)
         )

        CircleBackgroundWithIconInsideInCenter(boxSize = 55,
            iconSize = 25,
            boxBackgroud = DarkGreen,
            imageResource = R.drawable.filter
           , modifier = Modifier.align(Alignment.TopEnd))
    }

}
@Composable
fun Header() {
    Row(modifier = Modifier
        .fillMaxWidth()
        , horizontalArrangement = Arrangement.SpaceBetween)
    {
        CircleBackgroundWithIconInsideInCenter(
            boxSize = 50,
            iconSize = 35,
            boxBackgroud = LightGray,
            iconTintColor = Color.Black,
            imageResource = R.drawable.menu,
        )

        CircularImageComposable(icon = R.drawable.logo, modifier = Modifier
            .size(60.dp)
            .clip(
                CircleShape
            ))

        CircleBackgroundWithIconInsideInCenter(
            boxSize = 50,
            iconSize = 20,
            boxBackgroud = LightGray,
            iconTintColor = Color.Black,
            imageResource = R.drawable.bag,
        )
    }
}