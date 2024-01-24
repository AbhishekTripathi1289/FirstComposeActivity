package com.example.firstcomposeactivity.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.firstcomposeactivity.models.NormalListItem


@Composable
fun RecyclerViewItemCheckedUnCheckedCompose() {

    var items = remember {
        mutableStateOf( (1..20).map {
            NormalListItem("Item Number $it", false)
        })
    }

    LazyColumn(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally)
    {
        itemsIndexed(items.value) { i, item->

            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().clickable {
                items.value = items.value.mapIndexed { j, normalListItem ->
                    if(i == j)
                    {
                        if(item.isSelected == true)
                        {
                            normalListItem.copy(isSelected = false)
                        }
                        else{
                            normalListItem.copy(isSelected = true)
                        }
                    }
                    else{
                        normalListItem
                    }
                }

            }) {
                Text(text = item.text , fontSize = 25.sp)
                if(item.isSelected)
                {
                    Icon(Icons.Default.Check, contentDescription = "", tint = Color.Green)
                }
            }

        }
    }
}