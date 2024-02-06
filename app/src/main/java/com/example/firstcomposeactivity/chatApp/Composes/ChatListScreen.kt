package com.example.firstcomposeactivity.chatApp.Composes

import android.text.style.StyleSpan
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.firstcomposeactivity.compose.ConstraintLayoutExample
import com.example.firstcomposeactivity.models.ChatScreens
import com.example.firstcomposeactivity.models.Person
import com.example.firstcomposeactivity.models.personList
import com.example.firstcomposeactivity.ui.theme.Yellow

@Composable
fun ChatListScreen(navController: NavController) {

   Column(modifier = Modifier
       .fillMaxSize()
       .background(Color.Black)) {

       TopText(modifier = Modifier.padding(start = 16.dp, top = 24.dp))

       LazyRow(modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp)){

           item {
               AddStoryItemComposable()
           }

           itemsIndexed(personList){index, item ->
            UserStatusComposable(person = item)
           }
       }
       ChatListCompose(modifier = Modifier.padding(16.dp) ,navController)



   }
}

@Composable
fun ChatListCompose(modifier: Modifier = Modifier,navController: NavController)
{
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(top = 10.dp)
        .background(
            Color.White,
            RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
        ), horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier
            .padding(vertical = 8.dp)
            .width(100.dp)
            .height(5.dp)
            .background(Color.Gray, CircleShape))

        LazyColumn(modifier = Modifier.padding(bottom = 10.dp)){
            itemsIndexed(personList){ index, item ->
                UserChatItemComposable(person = item){
                navController.currentBackStackEntry?.savedStateHandle?.set<Person>("userData", it)
                    navController.navigate(ChatScreens.ChatDetailScreen.route)
                }
            }
        }
    }
}

@Composable
fun UserChatItemComposable(modifier: Modifier = Modifier, person: Person, callback: (Person)->Unit) {


    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp, top = 10.dp).clickable { callback.invoke(person) }) {

        ConstraintLayout(modifier = Modifier.fillMaxWidth()){

            var (image, name, okay, time) = createRefs()

            CircularImageComposable(icon = person.icon, modifier = Modifier
                .size(70.dp)
                .clip(
                    CircleShape
                )
                .padding(end = 8.dp)
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                })

            Text(text = person.name, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black,
                modifier = Modifier.constrainAs(name){
                    start.linkTo(image.end)
                })

            Text(text = "Okay", fontWeight = FontWeight.W400, fontSize = 14.sp, color = Color.Gray,
                modifier = Modifier
                    .constrainAs(okay) {
                        start.linkTo(image.end)
                    }
                    )

            createVerticalChain(name, okay, chainStyle = ChainStyle.Packed)


            Text(text = "12:23 PM",  fontWeight = FontWeight.W400, fontSize = 14.sp, color = Color.Gray,
                modifier = Modifier
                    .constrainAs(time) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
            )
        }

        Spacer(modifier = Modifier.padding(top = 10.dp).fillMaxWidth().height(1.dp).background(Color.Gray))

    }
}
@Composable
fun UserStatusComposable(modifier: Modifier = Modifier,
                         person: Person) {

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.padding(end = 8.dp)) {

        CircularImageComposable(icon = person.icon, modifier = Modifier
            .size(70.dp)
            .border(2.dp, Yellow, CircleShape)
            .padding(2.dp)
            .clip(CircleShape))
        Text(text = person.name, color = Color.White, modifier = Modifier.padding(top = 8.dp), fontSize = 16.sp)
    }
}

@Composable
fun CircularImageComposable(@DrawableRes icon: Int , modifier: Modifier)
{
    Image(painter = painterResource(id = icon), contentDescription = "", modifier = modifier)
}

@Composable
fun AddStoryItemComposable(modifier: Modifier = Modifier) {

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.padding(end = 8.dp)) {
        Box(
            modifier = modifier
                .size(70.dp)
                .background(Color.Yellow, CircleShape),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(Color.Black, CircleShape),
            ) {
                Icon(Icons.Default.Add, contentDescription = "", tint = Color.Yellow)
            }
        }

        Text(text = "Add Story",
            color = Color.White,
            modifier = Modifier.padding(top = 8.dp),
            fontSize = 16.sp)
    }
}
@Composable
fun TopText(modifier: Modifier)
{
    var annotatedString = buildAnnotatedString {

        withStyle(style = SpanStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.W300,
            color = Color.White
        )){
            append("Welcome Back")
        }
        withStyle(style = SpanStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )){
            append(" Jayant!")
        }
    }

    Text(text = annotatedString, modifier= modifier)

}