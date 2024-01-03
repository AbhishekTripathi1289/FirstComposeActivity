package com.example.firstcomposeactivity.quateAppCheezyCode.ui

import android.graphics.drawable.Icon
import android.widget.TextView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun QuoteListCompose(quoteList: Array<Quote>, callback: (Quote) -> Unit)
{
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Quotes App" , modifier = Modifier
            .fillMaxWidth().padding(top = 20.dp)
            , fontSize = 25.sp, textAlign = TextAlign.Center)

        LazyColumn()
        {
            itemsIndexed(quoteList)
            {index, item ->

                QuateListItem(quote = item) {
                    callback.invoke(it)
                }
            }
        }
    }
}


@Composable
fun QuateListItem(quote: Quote, onClick : (Quote) -> Unit) {

    Card(elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            .clickable { onClick.invoke(quote) })
    {

        Box(modifier = Modifier.background(Color.White)){
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.Top)
            {

                Image(imageVector = Icons.Filled.Close,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .background(Color.Black)
                        .size(40.dp))


                Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                    Text(text = quote.text,
                        style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold
                    )
                    Box(modifier = Modifier
                        .width(130.dp)
                        .height(1.dp)
                        .background(Color.LightGray)
                        .padding(vertical = 8.dp))
                    Text(text = quote.author?:"Auther", fontWeight = FontWeight.Light)

                }
            }
        }

    }
}




//@Preview(showSystemUi = true)
@Composable
fun QuateDetail(quote: Quote)
{

    BackHandler {
        DataManager.switchScreen(null)
    }

    Box(modifier = Modifier
        .fillMaxSize()

        .background(Brush.sweepGradient(colors = listOf(Color.Gray, Color.White))),
        contentAlignment = Alignment.Center)
    {

        Card(elevation = CardDefaults.cardElevation(8.dp),
            shape = RoundedCornerShape(8.dp), modifier = Modifier.padding(32.dp)) {


            Column(modifier = Modifier.padding(top = 30.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)) {

                Image(imageVector = Icons.Filled.Close,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .background(Color.Black)
                        .size(40.dp))

                Text(text = quote.text,
                    style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 10.dp)
                )

                Text(text = quote.author?:"Auther", fontWeight = FontWeight.Light,  modifier = Modifier.padding(top = 15.dp) , style = MaterialTheme.typography.bodyLarge)

            }

        }
    }

}


/*
@Composable
fun QuateListItemPreview() {
    QuateListItem()
}*/
