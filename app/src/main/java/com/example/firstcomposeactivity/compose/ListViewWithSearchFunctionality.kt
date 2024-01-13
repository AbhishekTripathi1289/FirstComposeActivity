package com.example.firstcomposeactivity.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListViewWithSearchFunctionality() {
    var typeText = remember {
        mutableStateOf("")
    }

    var list = remember {
        mutableStateOf(mutableListOf<String>("Abhishek","Tripathi", "Shivam", "Gupta", "Yogesh", "Ankit", "Faheem",
            "Sanjev", "Prince", "Gunjit"))
    }

    var filteredLIst = if(typeText.value.isEmpty()){
        list.value
    }else
    {
        list.value.filter { it.lowercase().contains(typeText.value.lowercase()) }
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            OutlinedTextField(value = typeText.value, onValueChange = {
                typeText.value = it
            }, label = {
                Text(text = "Search")
            }, placeholder = {
                Text(text = "Search")
            }, leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "")
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp))
        }

        items(filteredLIst){item->

            Text(text = item)
        }
    }
}