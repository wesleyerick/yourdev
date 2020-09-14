package com.wesleyerick.nytimesreviews.presenter

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.wesleyerick.nytimesreviews.model.ListMovies
import com.wesleyerick.nytimesreviews.view.RecyclerAdapter
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {

    companion object{

        var cacheSize = 10 * 1024 * 1024 // 10 MB


        private var okHttpClient = OkHttpClient()

        fun cacheConfig(context: Context){


            var cache = Cache(context.cacheDir,cacheSize.toLong())

        okHttpClient =
            OkHttpClient
                .Builder()
                .cache(cache)
                .addInterceptor { chain ->
                    var request = chain.request()
                    request = if (hasNetwork(context)!!)
                        request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                    else
                        request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                    chain.proceed(request)
                }
                .build()
        }

        fun getRetrofit(url: String) : Retrofit {
            return Retrofit
                .Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getRamdomMovies(){

            val callback = getRetrofit("https://api.nytimes.com/svc/movies/v2/reviews/")
                    .create(Service::class.java)
                    .getAllMovies()

            callback.enqueue(object : retrofit2.Callback<ListMovies> {
                override fun onResponse(
                    call: Call<ListMovies>,
                    response: Response<ListMovies>
                ) {
                    response.body().let {
                        RecyclerAdapter().configData(it!!.results)
                    }
                }

                override fun onFailure(call: Call<ListMovies>, t: Throwable) {
                    println("DEU RUIM NA LISTA ${t.message}")
                }
            })
        }

        fun hasNetwork(context: Context): Boolean? {
            var isConnected: Boolean? = false // Initial Value
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected)
                isConnected = true
            return isConnected
        }
    }
}