package com.raihan.mvpKotlin.api


import com.raihan.mvpKotlin.model.MovieList
import com.raihan.mvpKotlin.model.Movies
import com.raihan.mvpKotlin.util.Constants
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiServiceInterface {

    @GET("list/1?api_key=8e12b31fa510302c8d6ba7c5670b0407")
    fun getList(): Observable<MovieList>


    companion object Factory {
        fun create(): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}