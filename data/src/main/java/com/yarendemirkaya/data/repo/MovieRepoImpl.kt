package com.yarendemirkaya.data.repo

import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.data.datasource.local.MovieDAO
import com.yarendemirkaya.data.datasource.local.toDataModel
import com.yarendemirkaya.data.datasource.local.toDomainModel
import com.yarendemirkaya.data.datasource.model.response.toDomainModel
import com.yarendemirkaya.data.datasource.remote.MovieApi
import com.yarendemirkaya.domain.model.CartResponseModel
import com.yarendemirkaya.domain.model.FavMovieModel
import com.yarendemirkaya.domain.model.InsertMovieModel
import com.yarendemirkaya.domain.model.MovieCartModel
import com.yarendemirkaya.domain.model.MovieModel
import com.yarendemirkaya.domain.repo.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(
    private val dao: MovieDAO,
    private val api: MovieApi
) : MovieRepository {
    override suspend fun getAllMovies(): Flow<ResponseState<List<MovieModel>>> = flow {
        emit(ResponseState.Loading)
        try {
            val response = api.getAllMovies()
            if (response.isSuccessful) {
                val movies = response.body()?.movies?.map { it.toDomainModel() }
                if (movies != null) {
                    emit(ResponseState.Success(movies))
                } else {
                    emit(ResponseState.Error("No data found"))
                }
            }
        } catch (e: Exception) {
            emit(ResponseState.Error(e.message ?: "An error occurred"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun checkIfMovieIsFavorited(name:String): Boolean {
        return dao.checkIfMovieIsFavorited(name)
    }


    override suspend fun insertMovie(movieInsert: InsertMovieModel): Flow<ResponseState<CartResponseModel>> =
        flow {
            emit(ResponseState.Loading)
            try {
                val response = api.insertMovie(
                    movieInsert.name,
                    movieInsert.image,
                    movieInsert.price,
                    movieInsert.category,
                    movieInsert.rating,
                    movieInsert.year,
                    movieInsert.director,
                    movieInsert.description,
                    movieInsert.orderAmount,
                    "yaren_demirkaya",
                )
                if (response.isSuccessful) {
                    val cartResponse = response.body()?.toDomainModel()
                    if (cartResponse != null) {
                        emit(ResponseState.Success(cartResponse))
                    } else {
                        emit(ResponseState.Error("No data found"))
                    }
                }
            } catch (e: Exception) {
                emit(ResponseState.Error(e.message ?: "An error occurred"))
            }
        }


    override suspend fun getMovieCart(): Flow<ResponseState<List<MovieCartModel>>> =
        flow {
            emit(ResponseState.Loading)
            try {
                val response = api.getMovieCart("yaren_demirkaya")
                if (response.isSuccessful) {
                    val movies = response.body()?.movieCart?.map { it.toDomainModel() }
                    if (movies != null) {
                        emit(ResponseState.Success(movies))
                    } else {
                        emit(ResponseState.Error("No data found"))
                    }
                } else {
                    emit(ResponseState.Error("No data found"))
                }
            } catch (e: Exception) {
                emit(ResponseState.Error(e.message ?: "An error occurred"))
            }
        }

    override suspend fun deleteMovie(
        cartId: Int,
    ): Flow<ResponseState<CartResponseModel>> =
        flow {
            emit(ResponseState.Loading)
            try {
                val response = api.deleteMovie(cartId, "yaren_demirkaya")
                if (response.isSuccessful) {
                    val cartResponse = response.body()?.toDomainModel()
                    if (cartResponse != null) {
                        emit(ResponseState.Success(cartResponse))
                    } else {
                        emit(ResponseState.Error("No data found"))
                    }
                } else {
                    emit(ResponseState.Error("No data found"))
                }
            } catch (e: Exception) {
                emit(ResponseState.Error(e.message ?: "An error occurred"))
            }
        }


    override suspend fun getAllFavoriteMovies(): Flow<ResponseState<List<FavMovieModel>>> = flow {
        emit(ResponseState.Loading)
        try {
            val response = dao.getAllFavoriteMovies()
            if (response.isNotEmpty()) {
                emit(ResponseState.Success(response.map { it.toDomainModel() }))
            } else {
                emit(ResponseState.Error("No data found"))
            }
        } catch (e: Exception) {
            emit(ResponseState.Error(e.message ?: "An error occurred"))
        }
    }

    override suspend fun insertFavoriteMovie(favMovie: FavMovieModel) {
        dao.insertFavoriteMovie(favMovie.toDataModel())
    }

    override suspend fun deleteFavoriteMovie(favMovie: FavMovieModel) {
        dao.deleteFavoriteMovie(favMovie.toDataModel())
    }


}
