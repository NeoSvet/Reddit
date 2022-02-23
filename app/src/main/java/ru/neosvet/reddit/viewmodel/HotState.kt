package ru.neosvet.reddit.viewmodel

import ru.neosvet.reddit.list.Post

sealed class HotState {
    data class Success(val posts: List<Post>) : HotState()
    data class Error(val throwable: Throwable) : HotState()
}
