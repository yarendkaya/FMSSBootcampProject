package com.yarendemirkaya.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yarendemirkaya.data.db.entity.PersonEntity

@Database(entities = [PersonEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao

//    companion object {
//        @Volatile
//        private var instance: AppDatabase? = null
//        private var LOCK = Any()
//
//        operator fun invoke(context: Context) = instance
//            ?: synchronized(LOCK) {
//                instance ?: createDatabase(context).also {
//                    instance = it
//                }
//            }
//
//        private fun createDatabase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                AppDatabase::class.java,
//                "movie_database"
//            ).build()
//    }
}