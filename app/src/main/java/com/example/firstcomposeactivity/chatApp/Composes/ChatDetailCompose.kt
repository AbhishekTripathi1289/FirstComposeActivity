package com.example.firstcomposeactivity.chatApp.Composes

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.firstcomposeactivity.R
import com.example.firstcomposeactivity.compose.GenericCompose.CircularImageComposable
import com.example.firstcomposeactivity.models.Chat
import com.example.firstcomposeactivity.models.ChatScreens
import com.example.firstcomposeactivity.models.Person
import com.example.firstcomposeactivity.models.chatList
import com.example.firstcomposeactivity.ui.theme.Background
import com.example.firstcomposeactivity.ui.theme.LightRed
import com.example.firstcomposeactivity.ui.theme.LightYellow
import com.example.firstcomposeactivity.ui.theme.Pink80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatDetailCompose(navController: NavController) {


    var person = navController.previousBackStackEntry?.savedStateHandle?.get<Person>("userData")
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {

        TopHeaderCompose(person)

    

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
            .background(
                Color.White,
                RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )) {

            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 75.dp)){
                itemsIndexed(chatList){ index, item ->
                    ChatMessageItemCompose(chat = item)

                }
            }

            var textField = remember {
                mutableStateOf("")
            }
            TextField(value = textField.value, onValueChange = {
                textField.value = it
            },colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                disabledTextColor = Color.Transparent,
                containerColor = Pink80
                ),
                modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                , placeholder = {
                    Text(text = "Type something..." , fontSize = 18.sp, fontWeight = FontWeight.Normal,)
            },   leadingIcon = { CommonIconButton(imageVector = Icons.Default.Add) },
                trailingIcon = { CommonIconButtonDrawable(R.drawable.mic) }, shape = CircleShape,
                textStyle = TextStyle(fontSize = 18.sp) )
        }
    }
}


@Composable
fun CommonIconButtonDrawable(
    @DrawableRes icon: Int
) {
    Box(
        modifier = Modifier
            .background(Color.Yellow, CircleShape)
            .size(33.dp), contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = icon), contentDescription = "",
            tint = Color.Black,
            modifier = Modifier.size(15.dp)
        )
    }

}

@Composable
fun CommonIconButton(
    imageVector: ImageVector
) {

    IconButton(
        onClick = {

        }, modifier = Modifier
            .background(Color.Yellow, CircleShape)
            .size(33.dp)
    ) {
        androidx.compose.material.Icon(
            imageVector = imageVector,
            contentDescription = "",
            modifier = Modifier.size(15.dp), tint = Color.Black
        )
    }

}

@Composable
fun ChatMessageItemCompose(modifier: Modifier = Modifier, chat: Chat) {
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = if (chat.direction) Alignment.Start else Alignment.End
    ) {
        
        
        Text(text = chat.message,
            modifier = Modifier
                .background(if (chat.direction) LightRed else LightYellow, shape = CircleShape)
                .padding(10.dp)
            , color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.W600
        )

        Text(text = chat.time, color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.W300, modifier = Modifier.padding(top = 5.dp, start = 5.dp))
        
        
        
    }
}

@Composable
fun TopHeaderCompose(person: Person?) {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp, top = 30.dp)){

        var (image, name, okay, time) = createRefs()

        CircularImageComposable(icon = person?.icon?: R.drawable.ic_height, modifier = Modifier
            .size(60.dp)
            .clip(
                CircleShape
            )
            .padding(end = 8.dp)
            .constrainAs(image) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            })

        Text(text = person?.name?:"", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White,
            modifier = Modifier.constrainAs(name){
                start.linkTo(image.end)
            })

        Text(text = "Online", fontWeight = FontWeight.W400, fontSize = 14.sp, color = Color.Gray,
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
}