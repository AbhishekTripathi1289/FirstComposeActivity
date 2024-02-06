package com.example.firstcomposeactivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstcomposeactivity.NotesApp.composes.NotesListCompose
import com.example.firstcomposeactivity.NotesApp.viewmodel.NotesViewModel
import com.example.firstcomposeactivity.chatApp.Composes.MainNavigation
import com.example.firstcomposeactivity.compose.MainScreenWithBottomNavigation
import com.example.firstcomposeactivity.compose.NavigationDrawerExampleCompose
import com.example.firstcomposeactivity.compose.PracticeCompose
import com.example.firstcomposeactivity.compose.RadioGroupButtonCompose
import com.example.firstcomposeactivity.compose.SnackBarExample
import com.example.firstcomposeactivity.pizzaApp.composes.PizzAppHomeScreenCompose
import com.example.firstcomposeactivity.ui.theme.FirstComposeActivityTheme
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.spec.NavGraphSpec
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter",
        "UnusedMater" +
                "ialScaffoldPaddingParameter"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstComposeActivityTheme{
               MainNavigation()
            }
        }
    }



    @Preview(showBackground = true, showSystemUi = true,)
    @Composable
    fun GreetingPreview() {
        FirstComposeActivityTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
             //   ButtonInComposable()
            }
        }
    }
}

