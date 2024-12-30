package com.yarendemirkaya.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yarendemirkaya.data.datasource.model.response.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDAO


}