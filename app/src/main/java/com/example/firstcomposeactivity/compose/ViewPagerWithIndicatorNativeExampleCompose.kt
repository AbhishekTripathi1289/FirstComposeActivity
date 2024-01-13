package com.example.firstcomposeactivity.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun ViewPagerWithIndicatorNativeExampleCompose()
{
    var pagerStateValue = rememberPagerState()
    var coroutineScope = rememberCoroutineScope()
    var list = mutableListOf("Abhishek","Tripathi", "Shivam", "Gupta", "Yogesh", "Ankit", "Faheem",
        "Sanjev", "Prince", "Gunjit")

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        HorizontalPager(count = list.size, state = pagerStateValue) {
            Text(text = list.get(it))
        }

        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            OutlinedButton(onClick = { coroutineScope.launch { pagerStateValue.animateScrollToPage(pagerStateValue.currentPage+1) }}) {
                Text(text = "Click Me")
            }
            Row{
                repeat(list.size){
                    Indicator(pagerStateValue.currentPage == it)
                }
            }
        }

    }
}

@Composable
fun Indicator(isSelected : Boolean)
{
    Box(modifier = Modifier
        .padding(start = 10.dp)
        .background(if (isSelected) Color.Red else Color.Gray, shape = CircleShape)
        .size(15.dp))
}