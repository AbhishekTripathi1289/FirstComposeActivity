package com.example.firstcomposeactivity.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeactivity.R


@Composable
fun <T> ListViewComposable(list: ArrayList<T>, modifiers: Modifier = Modifier)
{
    LazyColumn(modifier = modifiers)
    {
        ListViewItem(list)
    }
}

fun <T> LazyListScope.ListViewItem(list: ArrayList<T>)
{
    itemsIndexed(list)
    {index, item->
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth())
        {
            Text(text = item as String, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}


@Composable
fun <T> ListViewWithProfileIcon(list : ArrayList<T>)
{

    LazyColumn()
    {
        itemsIndexed(list)
        { index: Int, item: T ->

            Card(shape = RoundedCornerShape(5.dp),
                elevation = CardDefaults.cardElevation(5.dp), modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp)
                    .padding(horizontal = 10.dp)
                    .background(
                        Color.White
                    )
                    ) {

                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.background(Color.White).fillMaxWidth().height(IntrinsicSize.Max).padding(8.dp)) {

                    Image(painter = painterResource(id = R.drawable.profile_icon),
                            contentDescription = "" ,
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp).weight(.2f))

                    VerticalTextView(modifiers = Modifier.padding(horizontal = 8.dp).weight(0.8f))
                }

            }
        }
    }
}

@Composable
private fun VerticalTextView(modifiers: Modifier = Modifier) {
    Column(modifier = modifiers) {
        Text(
            text = "Programming",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall
        )
        Text(text = "Learn Different Language", fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}