package ru.neosvet.reddit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.Disposable
import ru.neosvet.reddit.client.Reddit
import ru.neosvet.reddit.client.RedditRetrofit
import ru.neosvet.reddit.data.Listing
import ru.neosvet.reddit.list.Post
import ru.neosvet.reddit.scheduler.DefaultSchedulers
import ru.neosvet.reddit.scheduler.Schedulers

class HotViewModel(
    private val client: Reddit,
    private val schedulers: Schedulers
) : ViewModel() {
    companion object {
        private const val URL = "https://www.reddit.com"
    }
    private val _state: MutableLiveData<HotState> = MutableLiveData()
    val state: LiveData<HotState> = _state
    private var process: Disposable? = null

    override fun onCleared() {
        process?.dispose()
        super.onCleared()
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
            list.add(
                Post(
                    text = data.title,
                    link = URL + data.permalink,
                    stars = data.score,
                    comments = data.num_comments
                )
            )
        }
        return list
    }

    private fun postPosts(posts: List<Post>) {
        _state.postValue(HotState.Success(posts))
    }

    private fun postError(error: Throwable) {
        _state.postValue(HotState.Error(error))
    }
}