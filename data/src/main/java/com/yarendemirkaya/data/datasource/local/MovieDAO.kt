package com.yarendemirkaya.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yarendemirkaya.data.datasource.model.response.Movie
import com.yarendemirkaya.domain.model.FavMovieModel

@Dao
interface MovieDAO {

    @Query("SELECT * FROM movie_table")
    suspend fun getAllFavoriteMovies(): List<FavMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(favMovie: FavMovie)

    @Delete
    suspend fun deleteFavoriteMovie(favMovie: FavMovie)

}