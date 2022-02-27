package ru.neosvet.reddit.viewmodel

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.Single
import ru.neosvet.reddit.BuildConfig
import ru.neosvet.reddit.list.PagingConst
import ru.neosvet.reddit.list.Post
import ru.neosvet.reddit.repository.PostDao
import ru.neosvet.reddit.repository.PostEntity
import ru.neosvet.reddit.scheduler.Schedulers
import kotlin.math.roundToInt

class HotFactory(
    private val schedulers: Schedulers,
    private val storage: PostDao
) : RxPagingSource<Int, Post>() {
    private var total = 0

    fun calcTotal(count: Int) {
        total = (count / PagingConst.PAGE_SIZE.toFloat()).roundToInt()
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Post>> {
        val position = params.key ?: 0
        return storage.getPage(position)
            .subscribeOn(schedulers.background())
            .flatMap(this::parsePosts)
            .map { toLoadResult(it, position) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun parsePosts(result: List<PostEntity>): Single<List<Post>> {
        if (result.isEmpty())
            return Single.error(Exception())
        val list = mutableListOf<Post>()
        result.forEach {
            list.add(
                Post(
                    text = it.text,
                    link = BuildConfig.URL + it.link,
                    stars = it.stars,
                    comments = it.comments
                )
            )
        }
        return Single.just(list)
    }

    private fun toLoadResult(data: List<Post>, position: Int): LoadResult<Int, Post> {
        return LoadResult.Page(
            data = data,
            prevKey = if (position == 0) null else position - 1,
            nextKey = if (position + 1 == total) null else position + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return null
    }
}