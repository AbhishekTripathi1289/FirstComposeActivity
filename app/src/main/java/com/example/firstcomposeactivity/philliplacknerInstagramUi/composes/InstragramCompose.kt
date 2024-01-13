package com.example.firstcomposeactivity.philliplacknerInstagramUi.composes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeactivity.R
import com.example.firstcomposeactivity.models.InstragramStausModel


@Composable
fun InstagramUiCompose()
{

    val selectedTab = remember {
        mutableStateOf(0)
    }
    Column(modifier = Modifier.background(Color.White)) {
        
        HeaderCompose(modifierValue = Modifier)
        ProfileStatsCompose(modifiersValue = Modifier.padding(top = 15.dp))

        ProfileDescription(
            displayName = "Programming Mentor",
            description = "10 years of coding experience\n" +
                    "Want me to make your app? Send me an email!\n" +
                    "Subscribe to my YouTube channel!",
            url = "https://youtube.com/c/PhilippLackner",
            followedBy = listOf("codinginflow", "miakhalifa"),
            otherCount = 17
        )
        ChipHeaderCompose(modifierValue = Modifier.padding(top = 20.dp, start = 15.dp, end = 15.dp))
        StatusCraousalCompose(listOf(
            InstragramStausModel("youtoube", R.drawable.youtube),
            InstragramStausModel("Q&A", R.drawable.qa),
            InstragramStausModel("Discord", R.drawable.discord),
            InstragramStausModel("Teligram", R.drawable.telegram)),
            modifierValue = Modifier.padding(top = 20.dp, start = 15.dp, end = 15.dp))


        MediaTabCompose(modifierValue = Modifier.padding(top = 20.dp), listOf(
            InstragramStausModel("youtoube", R.drawable.ic_grid),
            InstragramStausModel("Q&A", R.drawable.ic_reels),
            InstragramStausModel("Discord", R.drawable.ic_igtv),
            InstragramStausModel("Teligram", R.drawable.profile))){
            selectedTab.value = it
            }

        when(selectedTab.value)
        {
            0 -> {
                PostCroousalCompose(modifierValue = Modifier, listOfImage = listOf(
                    painterResource(id = R.drawable.kmm),
                    painterResource(id = R.drawable.intermediate_dev),
                    painterResource(id = R.drawable.image_first),
                    painterResource(id = R.drawable.bad_habits),
                    painterResource(id = R.drawable.multiple_languages),
                    painterResource(id = R.drawable.learn_coding_fast),

                ))
            }
        }
    }

}


@Composable
fun PostCroousalCompose(modifierValue: Modifier, listOfImage: List<Painter>) {
    
    
    LazyVerticalGrid(columns = GridCells.Fixed(3))
    {
        itemsIndexed(listOfImage){index, item ->
            Image(painter = item, contentDescription = "",
                modifier = Modifier.aspectRatio(1f) .border(
                    width = 1.dp,
                    color = Color.White
                ),
                contentScale = ContentScale.Crop)
        }
    }
}

@Composable
fun MediaTabCompose(modifierValue: Modifier,
                    listOfTabs: List<InstragramStausModel>,
                    callback:(Int)->Unit) {

    
    var selectedPostion = remember {
        mutableStateOf(0)
    }

    TabRow(selectedTabIndex = selectedPostion.value, modifier = modifierValue)
    {
        listOfTabs.forEachIndexed{index, instragramStausModel ->

         Tab(selected = selectedPostion.value == index, onClick = {
             selectedPostion.value = index
             callback.invoke(index)
         }) {

            Icon(painter = painterResource(id = instragramStausModel.icon), contentDescription = "",
                tint = if(selectedPostion.value == index) Color.Black else Color(0xFF777777) , modifier = Modifier
                    .padding(10.dp)
                    .size(20.dp))
         }
        }
    }
}

@Composable
fun StatusCraousalCompose(list: List<InstragramStausModel>, modifierValue: Modifier) {
    LazyRow(modifier = modifierValue.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
        itemsIndexed(list){index, item ->
            InstagramStatusModelItemCompose(modifierValue = Modifier, item)
            Spacer(modifier = Modifier.width(5.dp))
        }
    }
}

@Composable
fun InstagramStatusModelItemCompose(
    modifierValue: Modifier,
    instragramStausModel: InstragramStausModel
)
{
    Image(painter = painterResource(id = instragramStausModel.icon),
        contentDescription = "",
        modifier = modifierValue
            .border(2.dp, LightGray, CircleShape)
            .padding(3.dp)
            .clip(CircleShape)
            .size(75.dp))

}

@Composable
fun ChipHeaderCompose(modifierValue : Modifier) {
    val width = 90.dp
    val height = 30.dp
    Row(modifier = modifierValue
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        ButtonWithImageCompose(modifierValue = Modifier
            .width(width)
            .height(height) , icon = Icons.Default.ArrowDropDown, "Following")
        ButtonWithImageCompose(modifierValue = Modifier
            .width(width)
            .height(height), text =  "Message")
        ButtonWithImageCompose(modifierValue = Modifier
            .width(width)
            .height(height) ,text =  "Email")
        ButtonWithImageCompose(modifierValue = Modifier.height(height) , icon = Icons.Default.ArrowDropDown)
    }

}


@Composable
fun ButtonWithImageCompose(modifierValue: Modifier, icon: ImageVector? = null, text: String? = null) {

    Row(modifier = modifierValue
        .border(width = 1.dp, color = LightGray, shape = RoundedCornerShape(5.dp))
        .padding(5.dp), horizontalArrangement = Arrangement.Center) {

        if(text?.isNotEmpty()== true)
        {
            Text(text = text, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
        if(icon != null)
        {
            Icon(icon, contentDescription = "", modifier = Modifier.size(20.dp))
        }
    }
}


@Composable
fun HeaderCompose(modifierValue: Modifier) {

    Row(modifier = modifierValue
        .fillMaxWidth()
        .padding(start = 20.dp, end = 20.dp, top = 25.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {

        Icon(Icons.Default.ArrowBack, contentDescription = ""
            , modifier = Modifier
                .size(24.dp)
            , tint = Color.Black)

        Text(text = "philliplackner_official", style = MaterialTheme.typography.bodyMedium)

        Icon(painter = painterResource(id = R.drawable.ic_bell), contentDescription = ""
            , modifier = Modifier
                .size(24.dp)
            , tint = Color.Black
        )

        Icon(painter = painterResource(id = R.drawable.ic_dotmenu), contentDescription = ""
            , modifier = Modifier
                .size(24.dp)
            , tint = Color.Black
        )

    }
}


@Composable
fun ProfileStatsCompose(modifiersValue : Modifier)
{
    Row(modifier = modifiersValue
        .fillMaxWidth()
        .padding(end = 15.dp, top = 25.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically) {

        Image(painter = painterResource(id = R.drawable.philipp), contentDescription = "", modifier = Modifier
            .size(95.dp)
            .border(2.dp, Color.LightGray, CircleShape)
            .clip(
                CircleShape
            ))

        StatsColumnCompose(modifierValue = Modifier, "601", "Posts")
        StatsColumnCompose(modifierValue = Modifier, "100K", "Followers")
        StatsColumnCompose(modifierValue = Modifier, "72", "Following")

    }

}

@Composable
fun StatsColumnCompose(modifierValue :Modifier, value: String, tag: String) {

    Column(modifier = modifierValue, horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = value, style = MaterialTheme.typography.bodyLarge)
        Text(text = tag, style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 10.dp))

    }
}

@Composable
fun ProfileDescription(
    displayName: String,
    description: String,
    url: String,
    followedBy: List<String>,
    otherCount: Int
) {
    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 10.dp)
    ) {
        androidx.compose.material.Text(
            text = displayName,
            fontWeight = FontWeight.Bold,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        androidx.compose.material.Text(
            text = description,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        androidx.compose.material.Text(
            text = url,
            color = Color(0xFF3D3D91),
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        if(followedBy.isNotEmpty()) {
            androidx.compose.material.Text(
                text = buildAnnotatedString {
                    val boldStyle = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    append("Followed by ")
                    followedBy.forEachIndexed { index, name ->
                        pushStyle(boldStyle)
                        append(name)
                        pop()
                        if (index < followedBy.size - 1) {
                            append(", ")
                        }
                    }
                    if (otherCount > 2) {
                        append(" and ")
                        pushStyle(boldStyle)
                        append("$otherCount others")
                    }
                },
                letterSpacing = letterSpacing,
                lineHeight = lineHeight
            )
        }
    }
}