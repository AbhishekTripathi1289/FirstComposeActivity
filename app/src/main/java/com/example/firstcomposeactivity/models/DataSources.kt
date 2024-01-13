package com.example.firstcomposeactivity.models



import androidx.annotation.DrawableRes
import com.example.firstcomposeactivity.R

data class Pager(
    @DrawableRes val image:Int,
    val des:String
)

val dataList = listOf(
    Pager(R.drawable.image_first,"PAGE ONE"),
    Pager(R.drawable.profile_icon,"PAGE TWO"),
    Pager(R.drawable.image_first,"PAGE THREE")
)