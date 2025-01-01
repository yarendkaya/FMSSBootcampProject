package com.yarendemirkaya.data.datasource.local

import androidx.room.Dao
import androidx.room.Query
import com.yarendemirkaya.data.datasource.model.response.Movie

@Dao
interface MovieDAO {

    @Query("SELECT * FROM movie_table")
    suspend fun getAllMovies(): List<Movie>
//
//    suspend fun insertMovie(movie: Movie)
//    suspend fun deleteMovie(movie: Movie)
//    suspend fun getMovieCart(userName: String): List<Movie>
//    //suspend fun clearCart(userName: String)
}