package com.example.firstcomposeactivity.compose

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeactivity.R
import kotlinx.coroutines.launch
import kotlin.random.Random

/*
var dataValue = remember {
    mutableStateOf(0)
}
Column(horizontalAlignment = Alignment.CenterHorizontally,
verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
    ShareStateBetweenComposableExampleFirst(dataValue.value){
        dataValue.value++
    }
    ShareStateBetweenComposableExampleSecond(value = dataValue.value)
}
*/
@Composable
fun ShareStateBetweenComposableExampleFirst(value : Int, callback:() -> Unit)
{
    Log.d("xxxyy", "Compose first")
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "you have sent $value Notification")
        Button(onClick = { callback.invoke()}, colors = ButtonDefaults.buttonColors(Color.Red),
            elevation = ButtonDefaults.buttonElevation(4.dp)) {
            Text(text = "Send Notification")
        }
    }
}


@Composable
fun ShareStateBetweenComposableExampleSecond(value : Int)
{
    Log.d("xxxyy", "Compose second")
    Card(elevation = CardDefaults.cardElevation(8.dp), shape = RoundedCornerShape(8.dp),  modifier = Modifier.padding(vertical = 20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
           Image( painter = painterResource(id = R.drawable.profile_icon), contentDescription = "", modifier = Modifier
               .size(30.dp)
               .clip(
                   CircleShape
               )
               .border(2.dp, Color.Black, CircleShape))
            Text(text = "Message sent so far- $value", modifier = Modifier.padding(horizontal = 10.dp),
                lineHeight = 10.sp,
                letterSpacing = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textDecoration = TextDecoration.Underline,
                )

        }
    }
}

@Composable
fun ImageComposable()
{

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.image_first),
            contentDescription = "",
            modifier = Modifier.size(100.dp)
                .border(3.dp, Color.Black, CircleShape)
                .clip(
                    CircleShape
                ))
    }

}


@Composable
fun ButtonInComposable(){

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize())
    {
        Button(colors = ButtonDefaults.buttonColors(contentColor = Color.Red,  containerColor = Color.Black),  onClick = { Log.d("dsaf", "dsfa")  }) {

            Text(text = "Hello world")
            Image(painter = painterResource(id = R.drawable.image_first), contentDescription ="kjj" , modifier = Modifier
                .width(30.dp)
                .height(30.dp)
                .padding(10.dp, 0.dp, 0.dp, 0.dp))

        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnackBarExample()
{
    val snackbarHostState = remember { SnackbarHostState() }
    val textViewState = remember {
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()


    Scaffold(modifier = Modifier.fillMaxSize(), snackbarHost = {
        SnackbarHost(snackbarHostState)
    }) {
        Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
            , modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .toggleable(
                    value = false,
                    onValueChange = {// TODO:  change here your required value like state     
                    },
                    role = Role.Checkbox
                )) {

            TextField(value = textViewState.value,
                onValueChange = {
                textViewState.value = it
            }, singleLine = true,
                modifier = Modifier.fillMaxWidth(), 
                label = { Text(text = "Plese Enter")})

            Spacer(modifier = Modifier.height(16.dp))

            Button( onClick = {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(message = "Hello ${textViewState.value}")
                }
            },
            ) {
                Text(text = "Click Me")
            }
        }
    }
}

//ImageComposable(contentDesc = "abc", painter = painterResource(id = R.drawable.image_first), titler = "This is my image ")
@Composable
fun ImageComposable(modifiers: Modifier = Modifier, contentDesc: String, painter: Painter, titler: String)
{

    Card(modifier = modifiers.fillMaxWidth(.6f),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    )
    {
        Box(modifier = Modifier.height(200.dp))
        {
            Image(painter = painter, modifier = Modifier.fillMaxSize(),
                contentDescription = contentDesc,
                contentScale = ContentScale.Crop)

            
            
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color.Black
                        ), startY = 300f
                    )
                ))

            Box(modifier = Modifier
                .background(Color.Transparent)
                .fillMaxSize()
                .padding(12.dp) , contentAlignment = Alignment.BottomStart
            )
            {

                Text(text = titler, style = TextStyle(color = Color.White, fontSize = 20.sp))
            }
        }
    }
}


/*val state = remember {
    mutableStateOf(Color.Red)
}
FirstComposeActivityTheme {
    Log.d("Compose_Tag", "My Compose")
    Column {

        ClickColorChange(modifiers = Modifier.fillMaxHeight(0.5f)){
            state.value = it
        }

        Box(modifier = Modifier
            .fillMaxHeight(0.5f)
            .fillMaxWidth()
            .background(state.value))
    }
}*/
@Composable
fun ClickColorChange(modifiers: Modifier = Modifier, callback : (Color)->Unit){

    var colorState = remember{
    mutableStateOf(Color.Red)
    }

    Box(modifier = modifiers
        .fillMaxWidth()
        .background(colorState.value)
        .clickable {
            callback.invoke(Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat()))
        })

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDetailExmplinationCompose()
{
    var data= remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        TextField(value = data.value, onValueChange = {
            data.value = it
        },
            label = {
                Text(text = "Text Field 1")
            },
            placeholder = {
                Text(text = "Please Enter Me")
            },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "")
            },
            trailingIcon = {
                Icon(Icons.Default.List, contentDescription = "")
            },
            isError = true,
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = Color.Red,
                containerColor = Color.Blue,
                textColor = Color.White),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            shape = RoundedCornerShape(10.dp)
        )
    }
}




