package com.wesleyerick.nytimesreviews.presenter

import com.wesleyerick.nytimesreviews.model.ItemCriticData
import com.wesleyerick.nytimesreviews.model.ListMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

//    val key = "u2ogZGoETqfPUyoS1het06oFlPH6D3IU"

    @GET("all.json?api-key=u2ogZGoETqfPUyoS1het06oFlPH6D3IU")
    fun getAllMovies() : Call<ListMovies>

    @GET("")
    fun getSearchMovies(
        @Query("movie") movie: String
    ): Call<ItemCriticData>

    @GET("")
    fun getMovieSelected() : Call<ItemCriticData>
}