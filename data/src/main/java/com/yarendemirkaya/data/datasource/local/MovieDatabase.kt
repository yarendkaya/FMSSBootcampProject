package com.yarendemirkaya.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavMovie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDAO
}