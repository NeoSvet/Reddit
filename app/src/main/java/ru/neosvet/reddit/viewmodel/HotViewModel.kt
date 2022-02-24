package ru.neosvet.reddit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import ru.neosvet.reddit.BuildConfig
import ru.neosvet.reddit.client.Reddit
import ru.neosvet.reddit.data.ChildData
import ru.neosvet.reddit.data.Listing
import ru.neosvet.reddit.list.Post
import ru.neosvet.reddit.repository.PostDao
import ru.neosvet.reddit.repository.PostEntity
import ru.neosvet.reddit.scheduler.Schedulers
import java.lang.Exception

class HotViewModel(
    private val client: Reddit,
    private val schedulers: Schedulers,
    private val storage: PostDao
) : ViewModel() {
    private val _state: MutableLiveData<HotState> = MutableLiveData()
    val state: LiveData<HotState> = _state
    private var process: Disposable? = null

    override fun onCleared() {
        process?.dispose()
        super.onCleared()
    }

    fun openHotPosts() {
        process = storage.getAll()
            .subscribeOn(schedulers.background())
            .flatMap(this::parsePosts)
            .observeOn(schedulers.main())
            .subscribe(
                this::postPosts
            ) {
                loadHotPosts()
            }
    }

    private fun parsePosts(result: List<PostEntity>): Single<List<Post>> {
        if(result.isEmpty())
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

    fun loadHotPosts() {
        process = client.getHot()
            .subscribeOn(schedulers.background())
            .map(this::parsePosts)
            .observeOn(schedulers.main())
            .subscribe(
                this::postPosts,
                this::postError
            )
    }

    private fun parsePosts(result: Listing): List<Post> {
        val list = mutableListOf<Post>()
        result.data.children.forEach {
            val data = it.data
            insertToStorage(data)
            list.add(
                Post(
                    text = data.title,
                    link = BuildConfig.URL + data.permalink,
                    stars = data.score,
                    comments = data.num_comments
                )
            )
        }
        return list
    }

    private fun insertToStorage(data: ChildData) {
        val entity = PostEntity(
            id = data.id,
            text = data.title,
            link = data.permalink,
            stars = data.score,
            comments = data.num_comments
        )
        storage.insert(entity).subscribe()
    }

    private fun postPosts(posts: List<Post>) {
        _state.postValue(HotState.Success(posts))
    }

    private fun postError(error: Throwable) {
        _state.postValue(HotState.Error(error))
    }
}