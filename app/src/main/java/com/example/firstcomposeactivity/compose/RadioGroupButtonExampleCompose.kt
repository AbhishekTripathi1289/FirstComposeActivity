package com.example.firstcomposeactivity.compose

import android.widget.CheckBox
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.firstcomposeactivity.R

@Composable
fun RadioGroupButtonCompose()
{
    var radioButtonList = remember {
        mutableStateOf(listOf("Male", "Female", "Others"))
    }
    var selected = remember {
        mutableStateOf("Male")
    }

    var isChecked = remember {
        mutableStateOf(true)
    }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        radioButtonList.value.forEach{
            Row {
                Text(text = it)
                RadioButton(selected = it == selected.value,
                    onClick = { selected.value = it})
            }
        }

        Checkbox(checked = isChecked.value , onCheckedChange ={
            isChecked.value = it
        } , colors = CheckboxDefaults.colors(checkmarkColor = colorResource(id = R.color.black),
            checkedColor = Color.Yellow))
    }
}