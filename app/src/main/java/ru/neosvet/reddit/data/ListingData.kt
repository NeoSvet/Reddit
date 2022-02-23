package ru.neosvet.reddit.data

import com.google.gson.annotations.SerializedName

data class ListingData(
    @SerializedName("after") val after : String,
    @SerializedName("dist") val dist : Int,
    @SerializedName("modhash") val modhash : String,
    @SerializedName("geo_filter") val geo_filter : String,
    @SerializedName("children") val children : List<Child>,
    @SerializedName("before") val before : String
)
