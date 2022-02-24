package ru.neosvet.reddit.repository

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: PostEntity): Completable

    @Update
    fun update(post: PostEntity): Completable

    @Delete
    fun delete(post: PostEntity): Completable

    @Query("SELECT * FROM Posts")
    fun getAll(): Single<List<PostEntity>>
}