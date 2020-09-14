package com.wesleyerick.nytimesreviews.model

import com.google.gson.annotations.SerializedName


data class ItemCriticData (

    @SerializedName("display_title")
    val title : String = "",

    @SerializedName("summary_short")
    val description : String = "",

//    @SerializedName("")
//    val photo : String = "",

    @SerializedName("byline")
    val criticName : String = "",

    @SerializedName("publication_date")
    val publicationDate : String = "",
)