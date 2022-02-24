package ru.neosvet.reddit.di

import org.koin.dsl.module
import ru.neosvet.reddit.client.Reddit
import ru.neosvet.reddit.client.RedditRetrofit
import ru.neosvet.reddit.repository.PostDao
import ru.neosvet.reddit.repository.RedditStorage
import ru.neosvet.reddit.scheduler.DefaultSchedulers
import ru.neosvet.reddit.scheduler.Schedulers
import ru.neosvet.reddit.viewmodel.HotViewModel

val appModule = module {
    single { HotViewModel(get(), get(), get()) }
    single<Reddit> { RedditRetrofit.create() }
    single<Schedulers> { DefaultSchedulers() }
    single<PostDao> { RedditStorage.create(get()).postDao() }
}