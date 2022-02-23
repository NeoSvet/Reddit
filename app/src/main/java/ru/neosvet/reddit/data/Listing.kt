package ru.neosvet.reddit.data

import com.google.gson.annotations.SerializedName

data class Listing(
    @SerializedName("kind") val kind: String,
    @SerializedName("data") val data: ListingData
)
