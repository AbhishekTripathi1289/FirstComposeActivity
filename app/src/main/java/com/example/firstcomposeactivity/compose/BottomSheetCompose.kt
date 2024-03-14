package com.example.firstcomposeactivity.compose


import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeactivity.models.NormalListItem
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetCompose() {

    var coroutineScope = rememberCoroutineScope()
    var bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    var scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)


    BottomSheetScaffold(sheetContent = {
        Box(modifier = Modifier.fillMaxWidth().height(300.dp), contentAlignment = Alignment.Center){
            Text(text = "I am bottom Sheet")
        }

    }, scaffoldState = scaffoldState, sheetBackgroundColor = Color.Green, sheetPeekHeight = 0.dp,
    ){
        Box(modifier = Modifier.fillMaxSize().pointerInput(Unit) {
            detectTapGestures(onTap = {
                coroutineScope.launch {
                    if (bottomSheetState.isExpanded) {
                        bottomSheetState.collapse()
                    }
                }
            })
        }, contentAlignment = Alignment.Center){
            Text(text = "Open BottomSheet", modifier = Modifier.clickable {
                if(bottomSheetState.isCollapsed)
                    coroutineScope.launch {
                        bottomSheetState.expand()
                    }
                else
                    coroutineScope.launch {
                        bottomSheetState.collapse()
                    }
            })
        }
    }
}
