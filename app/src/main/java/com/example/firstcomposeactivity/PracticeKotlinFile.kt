package com.example.firstcomposeactivity

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {


    runBlocking {
        println("Starting")

        CoroutineScope(Dispatchers.IO).launch {


            println(pracitce())
        }
        println("End Code")
    }

}

suspend fun pracitce(): String
{
    CoroutineScope(Dispatchers.IO).launch {
       // delay(5000)

        println("Coroutine IN method")

    }

    println("Method Return")


    return "ASdfg"


}