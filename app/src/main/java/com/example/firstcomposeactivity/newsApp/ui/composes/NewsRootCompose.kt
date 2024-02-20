package com.example.firstcomposeactivity.newsApp.ui.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.firstcomposeactivity.chatApp.Composes.GenericUrlImage
import com.example.firstcomposeactivity.compose.Indicator
import com.example.firstcomposeactivity.models.Screens
import com.example.firstcomposeactivity.newsApp.model.NewsAppScreen
import com.example.firstcomposeactivity.newsApp.model.NewsModel
import com.example.firstcomposeactivity.newsApp.viewmodel.NewsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun NewsRootCompose( modifier: Modifier, newsListL : List<NewsModel>, navController: NavController)
{
    var pagerStateValue = rememberPagerState()
        var coroutineScope = rememberCoroutineScope()

        VerticalPager(count = newsListL.size, state = pagerStateValue, modifier = modifier.fillMaxSize()) {

            Column {

                GenericUrlImage(imageUrl = newsListL.get(it).urlToImage, modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f).clickable { navController.navigate(NewsAppScreen.ImageScreen.withArgument(newsListL.get(it).urlToImage)) },
                    contentScale = ContentScale.FillBounds)

                Column(modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp , top = 15.dp)
                    .fillMaxSize()
                    .background(
                        Color.White,
                        RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                    )) {

                    Text(
                        text = newsListL.get(it).title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.Black,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(bottom = 15.dp)
                    )

                    Text(
                        text = newsListL.get(it).description,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.DarkGray,
                        maxLines = 10,
                        overflow = TextOverflow.Ellipsis
                    )

                }


            }
        }

}