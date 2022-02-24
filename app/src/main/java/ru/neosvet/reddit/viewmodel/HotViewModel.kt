package ru.neosvet.reddit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava3.flowable
import io.reactivex.rxjava3.disposables.Disposable
import ru.neosvet.reddit.client.Reddit
import ru.neosvet.reddit.data.ChildData
import ru.neosvet.reddit.data.Listing
import ru.neosvet.reddit.list.PagingConst
import ru.neosvet.reddit.repository.PostDao
import ru.neosvet.reddit.repository.PostEntity
import ru.neosvet.reddit.scheduler.Schedulers

class HotViewModel(
    private val client: Reddit,
    private val schedulers: Schedulers,
    private val storage: PostDao
) : ViewModel() {
    private val _state: MutableLiveData<HotState> = MutableLiveData()
    val state: LiveData<HotState> = _state
    private var process: Disposable? = null
    private val factory = HotFactory(schedulers, storage)

    override fun onCleared() {
        process?.dispose()
        super.onCleared()
    }

    fun paging() = Pager(
        config = PagingConfig(
            pageSize = PagingConst.PAGE_SIZE,
            prefetchDistance = PagingConst.PREFETCH_DISTANCE
        ),
        pagingSourceFactory = { factory }
    ).flowable

    fun preparing() {
        process = storage.getCount()
            .subscribeOn(schedulers.background())
            .subscribe { count ->
                if (count == 0)
                    loadHotPosts()
                else
                    factory.calcTotal(count)
            }
    }

    fun loadHotPosts() {
        _state.postValue(HotState.Loading)
        process = client.getHot()
            .subscribeOn(schedulers.background())
            .map(this::fillInStorage)
            .observeOn(schedulers.main())
            .subscribe(
                { postSuccess() },
                this::postError
            )
    }

    private fun fillInStorage(result: Listing) {
        factory.calcTotal(result.data.children.size)
        result.data.children.forEach {
            val data = it.data
            insertToStorage(data)
        }
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

    private fun postSuccess() {
        _state.postValue(HotState.Success)
    }

    private fun postError(error: Throwable) {
        _state.postValue(HotState.Error(error))
    }
}