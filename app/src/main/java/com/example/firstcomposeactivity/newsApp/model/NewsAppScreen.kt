package com.example.firstcomposeactivity.newsApp.model

sealed class NewsAppScreen(val route: String)
{
    object HomeScreen : NewsAppScreen("home_screen")
    object ImageScreen : NewsAppScreen("imageScreen")


    fun <T> withArgument(vararg args: T): String {
    return buildString {
        append(route)
        args.forEach {args->
            append("/$args")
        }
    }
    }
}
