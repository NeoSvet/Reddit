package ru.neosvet.reddit.viewmodel

sealed class HotState {
    object Success : HotState()
    object Loading : HotState()
    data class Error(val throwable: Throwable) : HotState()
}
