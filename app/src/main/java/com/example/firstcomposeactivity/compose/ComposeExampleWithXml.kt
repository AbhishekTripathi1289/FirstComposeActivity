package com.example.firstcomposeactivity.compose

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.firstcomposeactivity.R


@Composable
fun ComposeWithXml()
{
    AndroidView(factory = {
        View.inflate(it, R.layout.first_layout, null)
    },
        modifier = Modifier.fillMaxSize(),
        update = {
        var textview = it.findViewById<TextView>(R.id.textView)
            textview.setOnClickListener{
                textview.text = "Data Changed"
            }
    })
}