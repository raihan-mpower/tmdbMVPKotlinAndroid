package com.raihan.mvpKotlin.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.raihan.mvpKotlin.model.Movies

@Database(entities = [(Movies::class)], version = 1)
abstract class MovieLocalDb : RoomDatabase() {
    abstract fun movieListDao(): MoviesDao


    companion object Factory {
        private val dbName: String = "moviedb"

        fun create(context: Context): MovieLocalDb {
            var movieLocalDb =
                Room.databaseBuilder(context, MovieLocalDb::class.java!!, dbName).
                    allowMainThreadQueries().
                    build();



            return movieLocalDb
        }
    }
}