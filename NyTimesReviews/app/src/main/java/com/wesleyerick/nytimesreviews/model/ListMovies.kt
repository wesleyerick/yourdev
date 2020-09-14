package com.wesleyerick.nytimesreviews.model
import com.google.gson.annotations.SerializedName


data class ListMovies (

    @SerializedName("results")
    val results: ArrayList<ItemCriticData>
)