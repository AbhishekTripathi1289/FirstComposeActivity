package com.example.firstcomposeactivity.quateAppCheezyCode.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstcomposeactivity.quateAppCheezyCode.ui.ui.theme.FirstComposeActivityTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class QuotesListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            DataManager.loadAssetFromFile(applicationContext)
        }
        setContent {
            FirstComposeActivityTheme {
                loadCompose()

            }
        }
    }

    @Composable
    private fun loadCompose() {
       
        if(DataManager.isDataLoaded.value)
        {

            if(DataManager.navigation.value == DataManager.ScreenEnum.Listing)
            {
                QuoteListCompose(quoteList = DataManager.dataList) {
                    DataManager.switchScreen(it)
                }
            }
            else{
                DataManager.currentQuote?.let { QuateDetail(quote = it) }
            }
        }
        else
        {
            Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center){
                Text(text = "Loading")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstComposeActivityTheme {
        Greeting("Android")
    }
}