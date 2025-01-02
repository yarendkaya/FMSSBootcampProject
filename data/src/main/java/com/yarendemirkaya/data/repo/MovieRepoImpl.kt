package com.yarendemirkaya.data.repo

import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.data.datasource.local.MovieDatabase
import com.yarendemirkaya.data.datasource.model.response.toDomainModel
import com.yarendemirkaya.data.datasource.remote.MovieApi
import com.yarendemirkaya.domain.model.CartResponseModel
import com.yarendemirkaya.domain.model.InsertMovieRequestModel
import com.yarendemirkaya.domain.model.MovieModel
import com.yarendemirkaya.domain.repo.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(
    private val database: MovieDatabase,
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


    override suspend fun insertMovie(movieInsert: InsertMovieRequestModel): Flow<ResponseState<CartResponseModel>> =
        flow {
            emit(ResponseState.Loading)
            try {
                val response = api.insertMovie(movieInsert)
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
}
