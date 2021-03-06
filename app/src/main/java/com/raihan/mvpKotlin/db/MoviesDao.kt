package com.raihan.mvpKotlin.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.raihan.mvpKotlin.model.Movies
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getAll() : Maybe<List<Movies>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<Movies>)

    @Query("SELECT * FROM movies WHERE id = :id")
    fun findMovieWithId(id: String): Maybe<Movies>

}