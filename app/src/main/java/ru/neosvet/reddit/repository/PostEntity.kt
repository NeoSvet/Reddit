package ru.neosvet.reddit.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Posts")
data class PostEntity(
    @PrimaryKey val id: String,
    val text: String,
    val link: String,
    val stars: Int,
    val comments: Int
)
