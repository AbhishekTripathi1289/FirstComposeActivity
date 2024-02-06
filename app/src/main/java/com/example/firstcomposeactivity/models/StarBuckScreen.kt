package com.example.firstcomposeactivity.models

sealed class StarBuckScreen(val route: String)
{
    object StartScreen: StarBuckScreen("start_screen")
    object HomeScreen : StarBuckScreen("home_screen")
    object ProductDetailScreen : StarBuckScreen("product_detail_screen")


    fun <T> withArgument(vararg args: T): String {
    return buildString {
        append(route)
        args.forEach {args->
            append("/$args")
        }
    }
    }
}
