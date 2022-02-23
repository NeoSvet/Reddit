package ru.neosvet.reddit.client

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ru.neosvet.reddit.data.Listing

interface Reddit {
    @GET("r/aww/hot.json ")
    fun getHot(): Single<Listing>
}