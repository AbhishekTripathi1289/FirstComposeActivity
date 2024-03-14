package com.example.firstcomposeactivity.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeactivity.R
import com.example.firstcomposeactivity.models.Pager
import com.example.firstcomposeactivity.models.dataList
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator


@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun ViewPagerWithIndicatorLibraryExampleCompose()
{
    val pagerState = com.google.accompanist.pager.rememberPagerState()

    Column {
        com.google.accompanist.pager.HorizontalPager(count = dataList.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f))
        {
            PageItemComposable(dataList[it])
        }
        HorizontalPagerIndicator(pagerState = pagerState,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            activeColor = colorResource(id = R.color.black), indicatorWidth = 20.dp, indicatorHeight = 20.dp)

        AnimatedVisibility(visible = pagerState.currentPage == 2) {
          OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
              Text(text = "Continue")
          }
        }
    }
}

@Composable
fun PageItemComposable(pager: Pager)
{
    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(painter = painterResource(id = pager.image),
            contentDescription = pager.des , modifier = Modifier.size(200.dp))

        Text(text = pager.des, fontSize = 20.sp, style = MaterialTheme.typography.bodyLarge)

    }
}