package ru.neosvet.reddit.repository

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database(
    entities = [
        PostEntity::class
    ],
    version = 1
)
abstract class RedditStorage : RoomDatabase() {
    abstract fun postDao(): PostDao

    companion object {
        private const val DB_NAME = "database.db"

        fun create(context: Context) =
            Room.databaseBuilder(context, RedditStorage::class.java, DB_NAME)
                .build()
    }
}