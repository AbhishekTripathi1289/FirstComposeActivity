package com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.firstcomposeactivity.R
import com.example.firstcomposeactivity.models.BottomMenuContent
import com.example.firstcomposeactivity.models.Feature
import com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.ui.theme.AquaBlue
import com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.ui.theme.Beige1
import com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.ui.theme.Beige2
import com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.ui.theme.Beige3
import com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.ui.theme.BlueViolet1
import com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.ui.theme.BlueViolet2
import com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.ui.theme.BlueViolet3
import com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.ui.theme.ButtonBlue
import com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.ui.theme.DarkerButtonBlue
import com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.ui.theme.DeepBlue
import com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.ui.theme.LightGreen1
import com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.ui.theme.LightGreen2
import com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.ui.theme.LightGreen3
import com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.ui.theme.LightRed
import com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.ui.theme.OrangeYellow1
import com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.ui.theme.OrangeYellow2
import com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.ui.theme.OrangeYellow3
import com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.ui.theme.TextWhite


@Composable
fun HomeScreenCompose()
{
    Box(modifier = Modifier
        .background(DeepBlue)
        .padding(top = 25.dp)
        .fillMaxSize())
    {
        Column {
            Header()
            ChipSet(chips = listOf("Sweet Sleep", "Insomnia", "Depression"), modifier = Modifier.padding(top = 25.dp))
            HeaderCard(modifier = Modifier.padding(top= 25.dp))
            FeatureListCompose(fetureList = listOf(
                Feature(
                    title = "Sleep meditation",
                    R.drawable.ic_headphone,
                    BlueViolet1,
                    BlueViolet2,
                    BlueViolet3
                ),
                Feature(
                    title = "Tips for sleeping",
                    R.drawable.ic_videocam,
                    LightGreen1,
                    LightGreen2,
                    LightGreen3
                ),
                Feature(
                    title = "Night island",
                    R.drawable.ic_headphone,
                    OrangeYellow1,
                    OrangeYellow2,
                    OrangeYellow3
                ),
                Feature(
                    title = "Calming sounds",
                    R.drawable.ic_headphone,
                    Beige1,
                    Beige2,
                    Beige3
                ),
                Feature(
                    title = "Tips for sleeping",
                    R.drawable.ic_videocam,
                    LightGreen1,
                    LightGreen2,
                    LightGreen3
                ),
                Feature(
                    title = "Night island",
                    R.drawable.ic_headphone,
                    OrangeYellow1,
                    OrangeYellow2,
                    OrangeYellow3
                ),
                Feature(
                    title = "Calming sounds",
                    R.drawable.ic_headphone,
                    Beige1,
                    Beige2,
                    Beige3
                )
            ),  modifierValue = Modifier.padding(end = 15.dp))
        }

        BottomMenu(items = listOf(
            BottomMenuContent("Home", R.drawable.ic_home),
            BottomMenuContent("Meditate", R.drawable.ic_bubble),
            BottomMenuContent("Sleep", R.drawable.ic_moon),
            BottomMenuContent("Music", R.drawable.ic_music),
            BottomMenuContent("Profile", R.drawable.ic_profile),
        ), modifiersValue = Modifier
            .align(Alignment.BottomCenter)
           )
    }
}

@Composable
fun BottomMenu(items: List<BottomMenuContent>,
               modifiersValue: Modifier = Modifier,
               activeHighlightColor: Color = ButtonBlue,
               activeTextColor: Color = Color.White,
               inactiveTextColor: Color = AquaBlue,
               initialSelectedItemIndex: Int = 0)
{

    var selectedIndex = remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(modifier = modifiersValue.fillMaxWidth().background(DeepBlue).padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically) {

        items.forEachIndexed{index, bottomMenuContent ->  
            Column(modifier = Modifier.clickable { selectedIndex.value = index }) {
                    Box(modifier = Modifier.size(35.dp).clip(RoundedCornerShape(5.dp)).
                    background(if(selectedIndex.value == index) activeHighlightColor  else Color.Transparent )
                        .align(Alignment.CenterHorizontally),
                        contentAlignment = Alignment.Center)
                    {
                        Icon(painter = painterResource(id = bottomMenuContent.iconId), contentDescription = ""
                            , modifier = Modifier
                                .size(24.dp)
                               , tint = Color.White
                        )
                    }

                Text(text = bottomMenuContent.title, color = if(selectedIndex.value == index) activeTextColor else inactiveTextColor, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 5.dp))
            }
        }

    }

}

@Composable
fun Header(title: String  ="Abhishek")
{

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {

        Column(verticalArrangement = Arrangement.Center) {
            Text(text = "Good Morning $title", style = MaterialTheme.typography.bodySmall)
            Text(text = "We wish you have a good day!", style = MaterialTheme.typography.bodyLarge)
        }
        Icon(painter = painterResource(id = R.drawable.ic_search), contentDescription = ""
        , modifier = Modifier.size(24.dp), tint = Color.White
        )
    }
}


@Composable
fun ChipSet(chips: List<String>, modifier: Modifier)
{
    val selectedChipIndex = remember {
        mutableStateOf(0)
    }

    LazyRow() {
        items(chips.size) {
            
            Box(modifier = Modifier
                .padding(start = 15.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(if (it == selectedChipIndex.value) ButtonBlue else DarkerButtonBlue)
                .clickable { selectedChipIndex.value = it },
                contentAlignment = Alignment.Center)
            {
                Text(text = chips.get(it), modifier = Modifier.padding(vertical = 15.dp, horizontal = 10.dp),
                    color = Color.White)
            }
        }
    }
}

@Composable
fun HeaderCard(modifier: Modifier)
{

    Box(modifier = modifier
        .padding(horizontal = 15.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(5.dp))
        .height(85.dp)
        .background(LightRed), contentAlignment = Alignment.Center){

        Row(modifier= Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text(text = "Daily Though", style = MaterialTheme.typography.bodySmall)
                Text(text = "Meditation . 3 -10 min", style = MaterialTheme.typography.headlineSmall)
            }
            Box(modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue), contentAlignment = Alignment.Center)
            {
                Icon(painter = painterResource(id = R.drawable.ic_play), contentDescription = ""
                    , modifier = Modifier.size(15.dp), tint = Color.White
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FeatureListCompose(fetureList: List<Feature>, modifierValue: Modifier)
{

    Column(modifier = modifierValue.padding(top = 20.dp)) {

        Text(text = "Featured", style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 20.dp, start = 15.dp))

        LazyVerticalGrid( columns = GridCells.Fixed(2) ,
            verticalArrangement = Arrangement.SpaceBetween,
          )
        {
            items(fetureList)
            {
                ConstraintLayout(modifier = Modifier
                    .padding(start = 15.dp, bottom = 15.dp)
                    .height(130.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(
                        Brush.sweepGradient(
                            colors = listOf(
                                it.lightColor,
                                it.mediumColor,
                                it.endColor
                            )
                        )
                    )
                    .padding(bottom = 10.dp, top = 7.dp, start = 10.dp, end = 10.dp))
                {
                    val (textView, icon, button) = createRefs()

                    Text(text = "Good Morning ${it.title}",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .constrainAs(textView) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                            }, maxLines = 2,
                            overflow = TextOverflow.Ellipsis

                    )


                    Icon(painter = painterResource(id = it.iconId),
                        contentDescription = ""
                        , modifier = Modifier
                            .padding(bottom = 5.dp)
                            .size(20.dp)
                            .constrainAs(icon) {
                                start.linkTo(parent.start)
                                bottom.linkTo(parent.bottom)
                            }, tint = Color.White
                    )

                    Text(
                        text = "Start",
                        color = TextWhite,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable {
                                // Handle the click
                            }
                            .clip(RoundedCornerShape(10.dp))
                            .background(ButtonBlue)
                            .constrainAs(button) {
                                bottom.linkTo(parent.bottom)
                                end.linkTo(parent.end)
                            }
                            .padding(top = 6.dp, bottom = 6.dp, start = 15.dp, end = 15.dp)
                    )
                }
            }
        }
        }
}



