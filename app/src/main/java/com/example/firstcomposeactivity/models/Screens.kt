package com.example.firstcomposeactivity.models

sealed class Screens(val route: String)
{
    object MainScreen: Screens("main_screen")
    object DetailScreen : Screens("detail_screen")

    fun <T> withArgument(vararg args: T): String {
    return buildString {
        append(route)
        args.forEach {args->
            append("/$args")
        }
    }
    }
}
