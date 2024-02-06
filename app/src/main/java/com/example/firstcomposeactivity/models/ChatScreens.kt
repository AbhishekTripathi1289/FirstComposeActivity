package com.example.firstcomposeactivity.models

sealed class ChatScreens(val route: String)
{
    object HomeScreen: ChatScreens("home_screen")
    object ChatListSceen : ChatScreens("chat_list_screen")
    object ChatDetailScreen : ChatScreens("chat_detail_screen")


    fun <T> withArgument(vararg args: T): String {
    return buildString {
        append(route)
        args.forEach {args->
            append("/$args")
        }
    }
    }
}
