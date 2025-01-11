package com.yarendemirkaya.domain.repo

import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.CartResponseModel
import com.yarendemirkaya.domain.model.FavMovieModel
import com.yarendemirkaya.domain.model.InsertMovieModel
import com.yarendemirkaya.domain.model.MovieCartModel
import com.yarendemirkaya.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getAllMovies(): ResponseState<List<MovieModel>>
    suspend fun insertMovie(movieInsert: InsertMovieModel): ResponseState<String>
    suspend fun getMovieCart(): ResponseState<List<MovieCartModel>>
    suspend fun deleteMovie(cartId: Int): ResponseState<CartResponseModel>
    suspend fun getAllFavoriteMovies(): Flow<ResponseState<List<FavMovieModel>>>
    suspend fun insertFavoriteMovie(favMovie: FavMovieModel)
    suspend fun deleteFavoriteMovie(favMovie: FavMovieModel)
    suspend fun checkIfMovieIsFavorited(name: String): Boolean
}

