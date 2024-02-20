package com.example.firstcomposeactivity.chatApp.Composes

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firstcomposeactivity.R
import com.example.firstcomposeactivity.compose.GenericCompose.ButtonWithCornerComposable
import com.example.firstcomposeactivity.models.ChatScreens
import com.example.firstcomposeactivity.ui.theme.Aqua


@Composable
fun HomeScreenCompose(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)){

        Column(modifier = Modifier
            .fillMaxWidth()
            ) {
            Image(painter = painterResource(id = R.drawable.background), contentDescription = "")

            Text(text = stringResource(id = R.string.stay_with_your_friends),
                fontWeight = FontWeight.Bold
            , fontSize = 30.sp,
                modifier = Modifier.padding(top = 30.dp, start = 16.dp, end = 16.dp)
            , color = Color.White)



            Row(modifier = Modifier.padding(start = 16.dp, top = 20.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 10.dp)
                        .background(
                            Aqua,
                            RoundedCornerShape(
                                topEnd = 0.dp,
                                topStart = 0.dp,
                                bottomEnd = 80.dp,
                                bottomStart = 80.dp
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Check, contentDescription = "", tint = Color.Black, modifier = Modifier.size(16.dp))
                }
                
                Text(text = stringResource(id = R.string.secure_private_messaging), color = Color.White, fontSize = 18.sp)
            }
        }

        ButtonWithCornerComposable(modifier = Modifier.align(Alignment.BottomCenter)
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 16.dp).padding(bottom = 16.dp),
            backGroundColor = Color.White, foregroundColor = Color.Black,
            shape = CircleShape
            , fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            text = "Get Started"
        ){
            navController.navigate(ChatScreens.ChatListSceen.route)
        }
    }
}


@Composable
fun GenericImageCompose(modifier: Modifier, @DrawableRes res: Int,) {

    Image(painter = painterResource(id = res), contentDescription = "", modifier = modifier)
}

