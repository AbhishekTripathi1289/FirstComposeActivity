package com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.composes.HomeScreenCompose
import com.example.firstcomposeactivity.philiipLacknerFirstScreenCompose.ui.theme.FirstComposeActivityTheme
import com.example.firstcomposeactivity.philliplacknerInstagramUi.composes.InstagramUiCompose

class PhillipLackerHomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstComposeActivityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InstagramUiCompose()
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    FirstComposeActivityTheme {

    }
}