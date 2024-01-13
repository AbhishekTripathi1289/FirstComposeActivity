package com.example.firstcomposeactivity.models

data class Item(
    val id: Int,
    val name: String,
    val type: String
)

val itemList = listOf(
    Item(
        1, "Tiger", "Animal"
    ),
    Item(
        2, "Lion", "Animal"
    ),
    Item(
        3, "Deer", "Animal"
    ),
    Item(
        4, "Lotus", "Flower"
    ),
    Item(
        5, "Sunflower", "Flower"
    ),
    Item(
        6, "Rose", "Flower"
    ),
    Item(
        7, "Table", "Furniture"
    ),
    Item(
        8, "Chair", "Furniture"
    ),
    Item(
        9, "Bed", "Furniture"
    ),
    Item(
        10, "Monkey", "Animal"
    ),
)