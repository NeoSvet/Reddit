package ru.neosvet.reddit.list

data class Post(
    val text: String,
    val link: String,
    val stars: Int,
    val comments: Int
)
