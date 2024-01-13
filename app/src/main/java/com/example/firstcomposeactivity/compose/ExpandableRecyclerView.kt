package com.example.firstcomposeactivity.compose


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeactivity.models.itemList
import com.google.accompanist.pager.ExperimentalPagerApi


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun ExpandableRecyclerViewCompose()
{
    var animalmap = itemList.groupBy { it.type }

    var animalState = remember {
        mutableStateMapOf<String, Boolean>()
    }
    LazyColumn()
    {
        animalmap.forEach{ key, value ->

            item {

                Row(modifier = Modifier
                    .fillMaxWidth().toggleable(
                        value = animalState[key]==true,
                        onValueChange = {
                            animalState[key] = it
                        },
                        role = Role.Button
                    ).padding(horizontal = 10.dp)) {

                    Row(modifier = Modifier.weight(1f)) {
                        Text(text = key, style = MaterialTheme.typography.bodyLarge,
                            color= Color.Black, fontSize = 25.sp)

                        Text(text = "(${value.size})",  style = MaterialTheme.typography.bodyLarge,
                            color= Color.Black,
                            fontSize = 25.sp
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(if(animalState.get(key)== true) Icons.Default.KeyboardArrowUp else  Icons.Default.KeyboardArrowDown , contentDescription = "")
                    }
                }
            }

            if(animalState.get(key)== true)
            {
                items(value){
                    Text(text = it.name,style = MaterialTheme.typography.bodySmall,
                        color= Color.Black, fontSize = 20.sp ,modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp))
                }
            }

        }
    }

}