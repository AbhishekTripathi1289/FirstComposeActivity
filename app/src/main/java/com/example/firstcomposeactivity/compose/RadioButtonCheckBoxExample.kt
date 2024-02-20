package com.example.firstcomposeactivity.compose

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.firstcomposeactivity.R


data class CheckBoxState(var cehckBoxText: String, var isChecked: Boolean)
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

@Composable
fun CheckBoxComposable() {
    var listOFCheckBox = remember {
        mutableStateOf(
            mutableStateListOf(
            CheckBoxState("first", false),
            CheckBoxState("Second", false),
            CheckBoxState("Third", false)
        )
        )
    }

    LazyColumn{

        itemsIndexed(listOFCheckBox.value){index, item ->
            Checkbox(checked = item.isChecked, onCheckedChange = {
                Log.d("#####", "value of flag = $it")
                listOFCheckBox.value[index] = item.copy(isChecked = it)
                Log.d("#####", "value of arraylsit = $listOFCheckBox")

            }, colors = CheckboxDefaults.colors(checkmarkColor = colorResource(id = R.color.black),
                checkedColor = Color.Yellow))
        }

    }
}


@Composable
fun SwitchComposable() {

    var switchState = remember {
        mutableStateOf(CheckBoxState(cehckBoxText  = "Switch", isChecked = false))
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Switch(checked = switchState.value.isChecked, onCheckedChange = {
            switchState.value = switchState.value.copy(isChecked = it)
        })
    }
}

